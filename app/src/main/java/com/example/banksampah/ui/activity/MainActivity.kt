package com.example.banksampah.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.banksampah.R
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.factory.MainViewModelFactory
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Session3
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var session3: Session3
    lateinit var mainViewModel: MainViewModel
    var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MainRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        session3 = Session3(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getNasabah(session3.token!!)

        bottom_navigation.setupWithNavController(menuNavHostFragment.findNavController())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
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
}