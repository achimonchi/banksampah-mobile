package com.example.banksampah.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.databinding.FragmentAkunBinding
import com.example.banksampah.ui.activity.EditProfileActivity
import com.example.banksampah.ui.activity.LoginActivity
import com.example.banksampah.ui.viewmodel.AkunViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AkunFragment : Fragment() {

    private val akunViewModel: AkunViewModel by viewModels()
    private lateinit var dataBinding: FragmentAkunBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_akun, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.apply {
            lifecycleOwner = this@AkunFragment
            viewModel = akunViewModel
        }

        akunViewModel.apply {
            action.observe(viewLifecycleOwner, Observer { action ->
                when (action) {
                    AkunViewModel.ACTION_AKUN_LOGOUT -> akunLogout()
                    AkunViewModel.ACTION_AKUN_BTN_UBAHCLICK -> btnUbahOnClick()
                }
            })
        }
        akunViewModel.setAkun()
    }

    override fun onResume() {
        super.onResume()
        akunViewModel.setAkun()
    }

    private fun btnUbahOnClick() {
        startActivity(
            Intent(
                requireContext(),
                EditProfileActivity::class.java
            )
        )
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