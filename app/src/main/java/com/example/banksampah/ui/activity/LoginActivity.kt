package com.example.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.model.Auth
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.factory.MainViewModelFactory
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val repository = MainRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        session = Session(this)

        mainViewModel.getNasabah(session.token!!)
        mainViewModel.isVerified.observe(this, Observer {
            if (session.token != "") {
                if (it) {
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        })

        register.setOnClickListener(this)
        btn_daftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.register -> {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_daftar -> {
                val email = txt_email.text.toString().trim()
                val pass = txt_password.text.toString().trim()

                mainViewModel.login(Auth(email, pass))
                mainViewModel.userLoggedIn.observe(this, Observer { response ->
                    when (response) {
                        is Resource.Success -> {
                            response.data?.let { res ->
                                when (res.status) {
                                    HTTP_OK -> {
                                        emptyInputText()

                                        val token = res.data!!.token!!
                                        session.token = token

                                        mainViewModel.getNasabah(token)
                                        mainViewModel.isVerified.observe(
                                            this,
                                            Observer { verified ->
                                                if (verified) {
                                                    finish()
                                                    startActivity(
                                                        Intent(
                                                            this,
                                                            MainActivity::class.java
                                                        )
                                                    )
                                                } else {
                                                    finish()
                                                    startActivity(
                                                        Intent(
                                                            this,
                                                            VerifyActivity::class.java
                                                        )
                                                    )
                                                }
                                            }
                                        )
                                    }
                                    HTTP_UNAUTHORIZED -> {
                                        Snackbar.make(
                                            activity_login,
                                            "Password atau Email Salah",
                                            Snackbar.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                        is Resource.Error -> {
                            Snackbar.make(
                                activity_login,
                                response.message!!,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Loading -> {

                        }
                    }
                })
            }
        }
    }

    private fun emptyInputText() {
        txt_email.setText("")
        txt_password.setText("")
    }

}