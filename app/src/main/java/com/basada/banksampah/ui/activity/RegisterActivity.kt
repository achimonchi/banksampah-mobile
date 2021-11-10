package com.basada.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivityRegisterBinding
import com.basada.banksampah.ui.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var dataBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        dataBinding.apply {
            lifecycleOwner = this@RegisterActivity
            viewModel = registerViewModel
        }

        registerViewModel.apply {
            action.observe(this@RegisterActivity, Observer {
                when (it) {
                    RegisterViewModel.ACTION_REGISTER_SUCCESSFULLY -> registerSuccess()
                    RegisterViewModel.ACTION_REGISTER_FAILED -> registerFailed()
                    RegisterViewModel.ACTION_REGISTER_TIMEOUT -> registerFailed()
                    RegisterViewModel.ACTION_REGISTER_PASSWORD_NOT_MATCH -> registerPassNotMatch()
                    RegisterViewModel.ACTION_REGISTER_ONCLICK_LOGIN -> registerOnClickLogin()
                }
            })
        }
    }

    private fun registerOnClickLogin() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun registerSuccess() {
        Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun registerFailed() {
        Toast.makeText(this, registerViewModel.message, Toast.LENGTH_SHORT).show()
    }

    private fun registerPassNotMatch() {
        Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_SHORT).show()
    }

}