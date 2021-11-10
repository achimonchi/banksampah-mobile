package com.basada.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivityLoginBinding
import com.basada.banksampah.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var dataBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        dataBinding.apply {
            lifecycleOwner = this@LoginActivity
            viewModel = loginViewModel
        }

        loginViewModel.apply {
            action.observe(this@LoginActivity, Observer {
                when (it) {
                    LoginViewModel.ACTION_LOGIN_VERIFIED -> loginVerified()
                    LoginViewModel.ACTION_LOGIN_UNVERIFIED -> loginUnVerified()
                    LoginViewModel.ACTION_CONNECTION_TIMEOUT -> loginFailed()
                    LoginViewModel.ACTION_PASSWORD_INVALID -> passwordInvalid()
                    LoginViewModel.ACTION_LOGGED_IN -> loginVerified()
                    LoginViewModel.ACTION_BTN_REGISTER -> registerBtnOnClick()
                }
            })
            checkSession()
        }
    }

    private fun registerBtnOnClick() {
        finish()
        startActivity(
            Intent(
                this,
                RegisterActivity::class.java
            )
        )
    }

    private fun loginUnVerified() {
        finish()
        startActivity(
            Intent(
                this,
                VerifyActivity::class.java
            )
        )
    }

    private fun loginVerified() {
        finish()
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }

    private fun passwordInvalid() {
        Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show()
    }

    private fun loginFailed() {
        Toast.makeText(this, "Login gagal!, Coba ulangi lagi", Toast.LENGTH_SHORT).show()
    }

}