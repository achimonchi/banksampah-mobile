package com.basada.banksampah.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basada.banksampah.databinding.ItemGridKatalogBinding
import com.basada.banksampah.model.entity.SampahKategoryItem
import com.basada.banksampah.ui.viewmodel.TabViewModel

class RecyclerViewSampahAdapter(
    private val tabViewModel: TabViewModel? = null
) : RecyclerView.Adapter<RecyclerViewSampahAdapter.ViewHolder>() {

    val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<SampahKategoryItem>() {
        override fun areItemsTheSame(
            oldItem: SampahKategoryItem,
            newItem: SampahKategoryItem
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: SampahKategoryItem,
            newItem: SampahKategoryItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGridKatalogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        val itemSelected = diff.currentList[itemPosition]

        holder.binding.apply {
            position = itemPosition
            viewModel = tabViewModel
            item = itemSelected
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    inner class ViewHolder(val binding: ItemGridKatalogBinding) :
        RecyclerView.ViewHolder(binding.root)

}