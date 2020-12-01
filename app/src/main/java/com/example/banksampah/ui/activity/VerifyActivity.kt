package com.example.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivityVerifyBinding
import com.example.banksampah.ui.viewmodel.VerifyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VerifyActivity : AppCompatActivity() {

    private val verifyViewModel: VerifyViewModel by viewModels()
    private lateinit var dataBinding: ActivityVerifyBinding
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify)

        dataBinding.apply {
            lifecycleOwner = this@VerifyActivity
            viewModel = verifyViewModel
        }

        verifyViewModel.action.observe(this, Observer { action ->
            when (action) {
                VerifyViewModel.ACTION_VERIFY_SUCCESS -> verifySuccess()
                VerifyViewModel.ACTION_VERIFY_TIMEOUT -> verifyTimeout()
            }
        })
    }

    private fun verifyTimeout() {
        Toast.makeText(this, "Verifikasi Gagal", Toast.LENGTH_SHORT).show()
    }

    private fun verifySuccess() {
        finish()
        startActivity(
            Intent(this, MainActivity::class.java)
        )
    }

    override fun onBackPressed() {
        val toast =
            Toast.makeText(
                this,
                "Tekan Kembali sekali lagi untuk keluar",
                Toast.LENGTH_SHORT
            )

        if (doubleBackPressed) {
            toast.cancel()
            finish()
        }

        doubleBackPressed = true

        toast.show()

        GlobalScope.launch {
            delay(2000L)
            doubleBackPressed = false
        }
    }
}