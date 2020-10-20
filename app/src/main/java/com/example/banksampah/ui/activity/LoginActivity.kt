package com.example.banksampah.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.banksampah.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.register -> {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {
                val email = txt_email.text.toString().trim()
                val pass = txt_password.text.toString().trim()

                if (email == "admin@rich.com" && pass == "admin") {
                    Toast.makeText(
                        this@LoginActivity,
                        "Berhasil Login",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Email atau Password Salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}