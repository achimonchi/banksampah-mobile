package com.basada.banksampah.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basada.banksampah.databinding.ItemArtikelGridBinding
import com.basada.banksampah.model.entity.ArtikelResponseItem
import com.basada.banksampah.ui.viewmodel.HomeViewModel

class RecyclerViewArtikelAdapter(
    val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<RecyclerViewArtikelAdapter.ViewHolder>() {


    val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<ArtikelResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ArtikelResponseItem,
            newItem: ArtikelResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArtikelResponseItem,
            newItem: ArtikelResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArtikelGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        val currentItem = diff.currentList[itemPosition]

        holder.binding.apply {
            viewModel = homeViewModel
            position = itemPosition
            item = currentItem
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    inner class ViewHolder(val binding: ItemArtikelGridBinding) :
        RecyclerView.ViewHolder(binding.root)

}

