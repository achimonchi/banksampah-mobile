package com.example.banksampah.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.banksampah.R
import com.example.banksampah.utill.Session
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var session: Session
    var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        session = Session(this)

        bottom_navigation.setupWithNavController(menuNavHostFragment.findNavController())

        Toast.makeText(this, session.token, Toast.LENGTH_SHORT).show()
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