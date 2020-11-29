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
import com.example.banksampah.databinding.FragmentHomeBinding
import com.example.banksampah.ui.activity.JualSampahActivity
import com.example.banksampah.ui.activity.KatalogActivity
import com.example.banksampah.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.apply {
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
        }

        homeViewModel.action.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.ACTION_HOME_KTLG_KERTAS -> kertasOnClick()
                HomeViewModel.ACTION_HOME_KTLG_LOGAM -> logamOnClick()
                HomeViewModel.ACTION_HOME_KTLG_PLASTIK -> plastikOnClick()
                HomeViewModel.ACTION_HOME_KTLG_LAINNYA -> lainnyaOnClick()
                HomeViewModel.ACTION_HOME_JUALSAMPAH -> jualSampahOnClick()
            }
        })
    }

    private fun jualSampahOnClick() {
        startActivity(Intent(requireContext(), JualSampahActivity::class.java))
    }

    private fun kertasOnClick() {
        val intent = Intent(requireContext(), KatalogActivity::class.java).apply {
            putExtra(KatalogActivity.EXTRA_PAGE, 0)
        }
        startActivity(intent)
    }

    private fun lainnyaOnClick() {
        val intent = Intent(requireContext(), KatalogActivity::class.java).apply {
            putExtra(KatalogActivity.EXTRA_PAGE, 1)
        }
        startActivity(intent)
    }

    private fun plastikOnClick() {
        val intent = Intent(requireContext(), KatalogActivity::class.java).apply {
            putExtra(KatalogActivity.EXTRA_PAGE, 2)
        }
        startActivity(intent)
    }

    private fun logamOnClick() {
        val intent = Intent(requireContext(), KatalogActivity::class.java).apply {
            putExtra(KatalogActivity.EXTRA_PAGE, 3)
        }
        startActivity(intent)
    }


}