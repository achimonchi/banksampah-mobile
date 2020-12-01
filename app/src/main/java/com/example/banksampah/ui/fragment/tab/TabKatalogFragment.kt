package com.example.banksampah.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banksampah.R
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.ui.viewmodel.TabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_kertas.*

@AndroidEntryPoint
class TabKatalogFragment(
    val type: String
) : Fragment() {

    private val tabViewModel: TabViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    companion object {
        const val TYPE_KERTAS = "type_kertas"
        const val TYPE_LOGAM = "type_logam"
        const val TYPE_PLASTIK = "type_plastik"
        const val TYPE_LAIN = "type_lain"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = RecyclerViewAdapter(tabViewModel)
        return inflater.inflate(R.layout.fragment_kertas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        tabViewModel.apply {
            action.observe(viewLifecycleOwner, Observer {
                when (it) {
                    TabViewModel.ACTION_KERTAS_TIMEOUT -> connetionTimeout()
                    TabViewModel.ACTION_ITEM_UPDATE -> onItemUpdate()
                }
            })
            type = this@TabKatalogFragment.type
        }
        tabViewModel.setGrid()
    }

    private fun onItemUpdate() {
        adapter.diff.submitList(tabViewModel.list)
    }

    private fun connetionTimeout() {
        Toast.makeText(requireContext(), "Connection Timeout", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        rv_kertas.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = this@TabKatalogFragment.adapter
        }
    }

}