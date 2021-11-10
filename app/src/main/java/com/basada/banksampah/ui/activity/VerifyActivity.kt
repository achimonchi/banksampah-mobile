package com.basada.banksampah.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivityVerifyBinding
import com.basada.banksampah.ui.viewmodel.VerifyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class VerifyActivity : AppCompatActivity() {

    private val verifyViewModel: VerifyViewModel by viewModels()
    private lateinit var dataBinding: ActivityVerifyBinding
    private var doubleBackPressed = false

    private lateinit var date: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()

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
                VerifyViewModel.ACTION_VERIFY_DATEONCLICK -> dateOnClick()
                VerifyViewModel.ACTION_VERIFY_FORM_EMPTY -> onFormEmpty()
            }
        })

        initCalendar()
    }

    private fun onFormEmpty() {
        Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
    }

    private fun initCalendar() {
        date = DatePickerDialog.OnDateSetListener { _, year, month, day ->

            calendar.apply {
                set(Calendar.DAY_OF_MONTH, day)
                set(Calendar.MONTH, month)
                set(Calendar.YEAR, year)
            }

            val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.US)
            verifyViewModel.dateOfBirth.value = dateFormat.format(calendar.time)
        }
    }

    private fun dateOnClick() {
        DatePickerDialog(
            this,
            R.style.DatePicker,
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
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