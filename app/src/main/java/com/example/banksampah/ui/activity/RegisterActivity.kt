package com.example.banksampah.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.model.Auth
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Resource
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.txt_password
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mainRepository = MainRepository()
        mainViewModel = MainViewModel(mainRepository)

        tv_login.setOnClickListener(this)
        btn_daftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_daftar -> {
                val email = txt_username.text.toString()
                val password = txt_password.text.toString()
                val confirmPassword = txt_confirmPassword.text.toString()

                if (password == confirmPassword) {
                    mainViewModel.singup(Auth(email, password))
                    mainViewModel.userSignIn.observe(this, Observer { response ->
                        when (response) {
                            is Resource.Success -> {
                                response.data?.let { res ->
                                    when (res.status) {
                                        HTTP_OK -> {
                                            Toast.makeText(
                                                this,
                                                "Pendaftaran Berhasil!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            startActivity(Intent(this, LoginActivity::class.java))
                                        }
                                        HTTP_UNAUTHORIZED -> {
                                            Toast.makeText(
                                                this,
                                                res.data!!.message,
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
                } else {
                    Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}