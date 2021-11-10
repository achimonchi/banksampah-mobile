package com.basada.banksampah.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basada.banksampah.databinding.ItemRiwayatListBinding
import com.basada.banksampah.model.entity.GetRequestSampahResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecyclerViewRiwayatAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerViewRiwayatAdapter.ViewHolder>() {

    val diff =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<GetRequestSampahResponseItem>() {
            override fun areItemsTheSame(
                oldItemGet: GetRequestSampahResponseItem,
                newItemGet: GetRequestSampahResponseItem
            ): Boolean {
                return oldItemGet.id == newItemGet.id
            }

            override fun areContentsTheSame(
                oldItemGet: GetRequestSampahResponseItem,
                newItemGet: GetRequestSampahResponseItem
            ): Boolean {
                return oldItemGet.id == newItemGet.id
            }
        })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRiwayatListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        val currentItem = diff.currentList[itemPosition]

        holder.binding.apply {
            item = currentItem
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    inner class ViewHolder(val binding: ItemRiwayatListBinding) :
        RecyclerView.ViewHolder(binding.root)

}