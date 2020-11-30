package com.example.banksampah.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.banksampah.databinding.ItemGridKatalogBinding
import com.example.banksampah.model.entity.SampahKategoryItem
import com.example.banksampah.ui.viewmodel.TabViewModel

class RecyclerViewAdapter(
    private val recyclerViewModel: TabViewModel? = null
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

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
            viewModel = recyclerViewModel
            item = itemSelected
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onClickListener: ((SampahKategoryItem) -> Unit)? = null

    fun setOnClickListener(listener: (SampahKategoryItem) -> Unit) {
        onClickListener = listener
    }

    inner class ViewHolder(val binding: ItemGridKatalogBinding) :
        RecyclerView.ViewHolder(binding.root)

}