package com.example.banksampah.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivityRegisterBinding
import com.example.banksampah.ui.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var dataBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        registerViewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)

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
                }
            })
        }
    }

    private fun registerSuccess() {
        Toast.makeText(
            this,
            "Pendaftaran Berhasil!",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun registerFailed() {
        Toast.makeText(
            this,
            "Pendaftaran Gagal!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun registerPassNotMatch() {
        Toast.makeText(
            this,
            "Password tidak sama!",
            Toast.LENGTH_SHORT
        ).show()
    }

}