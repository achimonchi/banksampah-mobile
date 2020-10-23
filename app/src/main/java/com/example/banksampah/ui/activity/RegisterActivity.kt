package com.example.banksampah.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.banksampah.R
import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.Auth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.txt_password

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {
                val email = txt_username.text.toString()
                val password = txt_password.text.toString()
                val confirmPassword = txt_confirmPassword.text.toString()

                if (password == confirmPassword) {
                    val auth = Auth(email, password)
                } else {
                    Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}