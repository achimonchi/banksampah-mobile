package com.basada.banksampah.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.basada.banksampah.R
import com.basada.banksampah.databinding.FragmentHomeBinding
import com.basada.banksampah.ui.activity.JualSampahActivity
import com.basada.banksampah.ui.activity.KatalogActivity
import com.basada.banksampah.ui.adapter.RecyclerViewArtikelAdapter
import com.basada.banksampah.ui.viewmodel.HomeViewModel
import com.basada.banksampah.utill.Constant.URL_ARTIKEL
import com.basada.banksampah.utill.Constant.URL_REDIRECT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var artikelAdapter: RecyclerViewArtikelAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        artikelAdapter = RecyclerViewArtikelAdapter(homeViewModel)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        dataBinding.apply {
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
        }

        homeViewModel.action.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                HomeViewModel.ACTION_HOME_KTLG_KERTAS -> kertasOnClick()
                HomeViewModel.ACTION_HOME_KTLG_LOGAM -> logamOnClick()
                HomeViewModel.ACTION_HOME_KTLG_PLASTIK -> plastikOnClick()
                HomeViewModel.ACTION_HOME_KTLG_LAINNYA -> lainnyaOnClick()
                HomeViewModel.ACTION_HOME_JUALSAMPAH -> jualSampahOnClick()
                HomeViewModel.ACTION_HOME_SALDO_ONCLICK -> saldoOnClick()
                HomeViewModel.ACTION_HOME_PICKUP_ONCLICK -> pickupAndDropOnclick()
                HomeViewModel.ACTION_HOME_DROP_ONCLICK -> pickupAndDropOnclick()
                HomeViewModel.ACTION_HOME_ITEMUPDATE -> listItemUpdate()
                HomeViewModel.ACTION_HOME_ITEMONCLICK -> artkelItemOnClick()
                HomeViewModel.ACTION_HOME_TIMEOUT -> connectionTimeOut()
            }
        })
        homeViewModel.setGrid()
    }

    private fun artkelItemOnClick() {
        val itemArtikel = homeViewModel.listArtikel[homeViewModel.actionItemPosition.value ?: 0]

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("$URL_ARTIKEL/${itemArtikel.id}")
        }
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        dataBinding.rvArtikel.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = artikelAdapter
        }
    }

    private fun connectionTimeOut() {
        Toast.makeText(requireContext(), "Koneksi Gagal", Toast.LENGTH_SHORT).show()
    }

    private fun listItemUpdate() {
        artikelAdapter.diff.submitList(homeViewModel.listArtikel)
    }

    private fun pickupAndDropOnclick() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(URL_REDIRECT)
        }
        startActivity(intent)
    }

    private fun saldoOnClick() {
        findNavController().navigate(R.id.action_homeFragment_to_akunFragment)
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