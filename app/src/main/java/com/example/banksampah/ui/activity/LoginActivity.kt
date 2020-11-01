package com.example.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.model.Auth
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.android.synthetic.main.activity_login.*
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class LoginActivity : AppCompatActivity(R.layout.activity_login), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainRepository = MainRepository()
        mainViewModel = MainViewModel(mainRepository)
        session = Session(this)

        if (session.token != "") {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

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
                                        session.token = res.data!!.token!!
                                        emptyInputText()
                                        finish()
                                        startActivity(Intent(this, MainActivity::class.java))
                                    }
                                    HTTP_UNAUTHORIZED -> {
                                        Toast.makeText(
                                            this,
                                            "Password atau Email Salah",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
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