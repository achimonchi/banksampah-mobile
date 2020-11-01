package com.example.banksampah.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.banksampah.R
import com.example.banksampah.ui.activity.LoginActivity
import com.example.banksampah.utill.Session
import kotlinx.android.synthetic.main.fragment_akun.*

class AkunFragment : Fragment(R.layout.fragment_akun), View.OnClickListener {

    lateinit var session: Session

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = Session(requireContext())

        btn_logout_akun.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_logout_akun -> {
                if (session.token != "") {
                    session.token = ""

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    requireActivity().finish()
                    startActivity(intent)
                }
            }
        }
    }
}