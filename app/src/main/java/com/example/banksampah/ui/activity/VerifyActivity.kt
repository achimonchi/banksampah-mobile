package com.example.banksampah.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.model.NasabahUpdate
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.factory.MainViewModelFactory
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class VerifyActivity : AppCompatActivity(R.layout.activity_verify), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var session: Session
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MainRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        session = Session(this)

        Toast.makeText(this, session.token, Toast.LENGTH_SHORT).show()

        btn_changeProfile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_changeProfile -> {
                val nama = txtNamaLengkap.text.toString()
                val noHp = txtNoHp.text.toString()
                val alamat = txtAlamat.text.toString()
                val kota = txtKota.text.toString()
                val provinsi = txtProvinsi.text.toString()
                val kodePos = txtKodePos.text.toString()

                val nasabah = NasabahUpdate(
                    nName = nama,
                    nAddress = alamat,
                    nCity = kota,
                    nProvince = provinsi,
                    nPostcode = kodePos,
                    nContact = noHp
                )

                mainViewModel.updateNasabah(session.token!!, nasabah)
                mainViewModel.nasabahUpdate.observe(this, Observer { response ->
                    when (response) {
                        is Resource.Success -> {
                            response.data?.let { res ->
                                when (res.status) {
                                    HTTP_OK -> {
                                        finish()
                                        startActivity(
                                            Intent(this, MainActivity::class.java)
                                        )
                                    }
                                    HTTP_UNAUTHORIZED -> {
                                        Toast.makeText(this, res.data?.message, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                    }
                })
            }
        }
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
            session.token = ""
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