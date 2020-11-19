package com.example.banksampah.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.banksampah.R
import com.example.banksampah.ui.activity.KatalogActivity
import com.example.banksampah.ui.activity.MainActivity
import com.example.banksampah.ui.viewmodel.MainViewModel
import com.example.banksampah.utill.Session3
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    lateinit var session3: Session3
    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        session3 = Session3(requireContext())

        mainViewModel = (requireActivity() as MainActivity).mainViewModel

        kertas.setOnClickListener(this)
        logam.setOnClickListener(this)
        plastik.setOnClickListener(this)
        katalogLainnya.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.kertas -> {
                startActivity(Intent(requireContext(), KatalogActivity::class.java))
            }
        }
    }
}