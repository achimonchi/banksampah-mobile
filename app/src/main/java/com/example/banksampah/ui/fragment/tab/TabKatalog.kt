package com.example.banksampah.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banksampah.R
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.ui.viewmodel.KertasViewModel
import kotlinx.android.synthetic.main.fragment_kertas.*

class TabKatalog(
    val type: String
) : Fragment() {

    private lateinit var kertasViewModel: KertasViewModel
    private lateinit var adapter: RecyclerViewAdapter

    companion object {
        const val TYPE_KERTAS = "type_kertas"
        const val TYPE_LOGAM = "type_logam"
        const val TYPE_PLASTIK = "type_plastik"
        const val TYPE_LAIN = "type_lain"

        fun newInstance(type: String): TabKatalog {
            return TabKatalog(type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kertasViewModel = ViewModelProvider(this).get(KertasViewModel::class.java)
        adapter = RecyclerViewAdapter(kertasViewModel)
        return inflater.inflate(R.layout.fragment_kertas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        kertasViewModel.apply {
            action.observe(viewLifecycleOwner, Observer {
                when (it) {
                    KertasViewModel.ACTION_KERTAS_TIMEOUT -> connetionTimeout()
                    KertasViewModel.ACTION_ITEM_UPDATE -> onItemUpdate()
                }
            })
            actionItemClick.observe(viewLifecycleOwner, Observer {
                onItemClick(it)
            })
            type = this@TabKatalog.type
        }
        kertasViewModel.setGrid()
    }

    private fun onItemClick(position: Int) {
        val list = adapter.diff.currentList[position]

        Toast.makeText(requireContext(), list.jPrice, Toast.LENGTH_SHORT).show()
    }

    private fun onItemUpdate() {
        adapter.diff.submitList(kertasViewModel.list)
    }

    private fun connetionTimeout() {
        Toast.makeText(requireContext(), "Connection Timeout", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        rv_kertas.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = this@TabKatalog.adapter
        }
    }

}