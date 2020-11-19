package com.example.banksampah.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.model.Nasabah
import com.example.banksampah.ui.activity.LoginActivity
import com.example.banksampah.ui.activity.MainActivity
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Converter
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session3
import kotlinx.android.synthetic.main.fragment_akun.*
import java.lang.StringBuilder

class AkunFragment : Fragment(R.layout.fragment_akun), View.OnClickListener {

    lateinit var session3: Session3
    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session3 = Session3(requireContext())

        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        mainViewModel.userData.observe(viewLifecycleOwner, Observer {
            setAkun(it)
        })

        btn_logout_akun.setOnClickListener(this)
    }

    private fun setAkun(res: Resource<Nasabah>) {
        when (res) {
            is Resource.Success -> {
                res.data?.let { response ->
                    txtNamaProfil.text = StringBuilder(response.nName.toString())
                    txtNoHpProfil.text = StringBuilder(response.nContact.toString())
                    txtEmailProfil.text = StringBuilder(response.fkAuth!!)
                    txtSaldo.text = StringBuilder(Converter.intToString(response.nBalance?.toInt()!!))
                }
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {

            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_logout_akun -> {
                if (session3.token != "") {
                    session3.token = ""

                    requireActivity().finish()

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}