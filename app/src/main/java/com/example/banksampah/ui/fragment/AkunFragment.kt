package com.example.banksampah.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.databinding.FragmentAkunBinding
import com.example.banksampah.ui.activity.LoginActivity
import com.example.banksampah.ui.viewmodel.AkunViewModel

class AkunFragment : Fragment() {

    private lateinit var akunViewModel: AkunViewModel
    private lateinit var dataBinding: FragmentAkunBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_akun, container, false)
        akunViewModel = ViewModelProvider(this).get(AkunViewModel::class.java)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.apply {
            lifecycleOwner = this@AkunFragment
            viewModel = akunViewModel
        }

        akunViewModel.apply {
            action.observe(viewLifecycleOwner, Observer {
                when (it) {
                    AkunViewModel.ACTION_AKUN_LOGOUT -> akunLogout()
                }
            })
            setAkun()
        }

    }

    private fun akunLogout() {
        requireActivity().finish()
        startActivity(
            Intent(
                requireContext(),
                LoginActivity::class.java
            )
        )
    }

}