package com.basada.banksampah.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.basada.banksampah.R
import com.basada.banksampah.databinding.FragmentRiwayatBinding
import com.basada.banksampah.ui.adapter.RecyclerViewRiwayatAdapter
import com.basada.banksampah.ui.viewmodel.RiwayatViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RiwayatFragment : Fragment() {

    @Inject
    lateinit var riwayatAdapter: RecyclerViewRiwayatAdapter
    private val riwayatViewModel: RiwayatViewModel by viewModels()
    lateinit var dataBinding: FragmentRiwayatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_riwayat, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.apply {
            lifecycleOwner = this@RiwayatFragment
            viewModel = riwayatViewModel
        }

        setUpRecyclerView()
        riwayatViewModel.apply {
            action.observe(viewLifecycleOwner, Observer { action ->
                when (action) {
                    RiwayatViewModel.ACTION_RIWAYAT_TIMEOUT -> connectionTimeOut()
                    RiwayatViewModel.ACTION_RIWAYAT_UPDATE -> onItemUpdate()
                }
            })
        }
        riwayatViewModel.setList()
    }

    private fun onItemUpdate() {
        riwayatAdapter.diff.submitList(riwayatViewModel.list)
    }

    private fun connectionTimeOut() {
        Toast.makeText(requireContext(), "Connection Timeout", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        dataBinding.rvRiwayat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@RiwayatFragment.riwayatAdapter
        }
    }

}