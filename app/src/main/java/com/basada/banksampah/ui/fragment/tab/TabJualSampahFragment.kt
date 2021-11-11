package com.basada.banksampah.ui.fragment.tab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.basada.banksampah.R
import com.basada.banksampah.model.entity.SampahKategoryItem
import com.basada.banksampah.ui.activity.RequestActivity
import com.basada.banksampah.ui.adapter.RecyclerViewSampahAdapter
import com.basada.banksampah.ui.viewmodel.TabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_kertas.*

@AndroidEntryPoint
class TabJualSampahFragment(
    val type: String,
    val id: String
) : Fragment() {

    private val tabViewModel: TabViewModel by viewModels()
    private lateinit var sampahAdapter: RecyclerViewSampahAdapter

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
        sampahAdapter = RecyclerViewSampahAdapter(tabViewModel)
        return inflater.inflate(R.layout.fragment_kertas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        Log.d("Load item with id : ",id);
        tabViewModel.apply {
            action.observe(viewLifecycleOwner, Observer { action ->
                when (action) {
                    TabViewModel.ACTION_KERTAS_TIMEOUT -> connetionTimeout()
                    TabViewModel.ACTION_ITEM_UPDATE -> onItemUpdate()
                    TabViewModel.ACTION_ITEM_ONCLICK -> onItemClick()
                }
            })
            type = this@TabJualSampahFragment.type
        }
        tabViewModel.setGrid(id)
    }

    private fun onItemClick() {
        Log.d("Item di klik : ",id);
        tabViewModel.actionItemClick.value?.let { position ->
            try {
                val item = sampahAdapter.diff.currentList[position]

                val intent = Intent(requireContext(), RequestActivity::class.java)
                intent.putExtra(RequestActivity.EXTRA_ITEM, item)
                startActivity(intent)
            } catch (e: IndexOutOfBoundsException) {
            }
        }
    }

    private fun onItemUpdate() {
        Log.d("Item Update w ID : ",id);
        Log.d("Item Update w Type : ",type);
        Log.d("Tab View Model List : ",tabViewModel.list.toString());
        var obj: SampahKategoryItem = tabViewModel.list.get(0);
        for(e in tabViewModel.list){
            if(e.fkKategori == id) {
                obj = e;
            }
        }
        Log.d("Item Selected w ID:",obj.fkKategori.toString());
        Log.d("Item Selected w name:",obj.jName.toString());

        sampahAdapter.diff.submitList(tabViewModel.list)
    }

    private fun connetionTimeout() {
        Toast.makeText(requireContext(), "Connection Timeout", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        rv_kertas.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = this@TabJualSampahFragment.sampahAdapter
        }
    }

}