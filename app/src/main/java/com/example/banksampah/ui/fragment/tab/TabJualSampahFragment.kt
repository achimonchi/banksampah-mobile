package com.example.banksampah.ui.fragment.tab

import android.content.Intent
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
import com.example.banksampah.ui.activity.RequestActivity
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.ui.viewmodel.TabViewModel
import kotlinx.android.synthetic.main.fragment_kertas.*

class TabJualSampahFragment(
    val type: String
) : Fragment() {

    private lateinit var tabViewModel: TabViewModel
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
        tabViewModel = ViewModelProvider(this).get(TabViewModel::class.java)
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
            actionItemClick.observe(viewLifecycleOwner, Observer { position ->
                isItemClicked.observe(viewLifecycleOwner, Observer { isClick ->
                    onItemClick(position, isClick)
                })
            })
            type = this@TabJualSampahFragment.type
        }
        tabViewModel.setGrid()
    }

    private fun onItemClick(position: Int, isClick: Boolean) {
        val item = adapter.diff.currentList[position]

        if (!isClick) {
            val intent = Intent(requireContext(), RequestActivity::class.java)
            intent.putExtra(RequestActivity.EXTRA_ITEM, item)
            startActivity(intent)

            tabViewModel.isItemClicked.value = true
        }
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
            adapter = this@TabJualSampahFragment.adapter
        }
    }

}