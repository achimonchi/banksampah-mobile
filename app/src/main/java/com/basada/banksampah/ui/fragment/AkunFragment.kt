package com.basada.banksampah.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.FragmentAkunBinding
import com.basada.banksampah.ui.activity.EditProfileActivity
import com.basada.banksampah.ui.activity.LoginActivity
import com.basada.banksampah.ui.viewmodel.AkunViewModel
import com.basada.banksampah.utill.Constant.URL_BANTUAN
import com.basada.banksampah.utill.Constant.URL_REDIRECT
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
                    AkunViewModel.ACTION_AKUN_AGEN_ONCLICK -> agenAndDropOnClick()
                    AkunViewModel.ACTION_AKUN_DROP_ONCLICK -> agenAndDropOnClick()
                    AkunViewModel.ACTION_AKUN_BANTUAN_ONCLICK -> bantuanOnClick()
                }
            })
        }
        akunViewModel.setAkun()
    }

    private fun bantuanOnClick() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(URL_BANTUAN)
        }
        startActivity(intent)
    }

    private fun agenAndDropOnClick() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(URL_REDIRECT)
        }
        startActivity(intent)
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