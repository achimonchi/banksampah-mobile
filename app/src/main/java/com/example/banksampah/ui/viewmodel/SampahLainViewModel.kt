package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.SampahKategoryItem
import com.example.banksampah.model.SampahKategoryResponse
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class SampahLainViewModel : BaseViewModel() {

    companion object {
        const val ACTION_LAIN_TIMEOUT = "action_kertas_timeout"
    }

    fun setGrid(
        adapter: RecyclerViewAdapter,
        listener: (SampahKategoryItem) -> Unit
    ) {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response =
                repository.getSampahByCategory(
                    Session.token ?: "",
                    "3c82981a5af24542ca9771ca9175877f"
                )) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)
                    adapter.diff.submitList(response.data?.data)
                    adapter.setOnClickListener {
                        listener(it)
                    }
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_LAIN_TIMEOUT)
                }
            }
        }
    }

}