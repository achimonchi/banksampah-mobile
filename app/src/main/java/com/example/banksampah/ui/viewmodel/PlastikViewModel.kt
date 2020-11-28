package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.SampahKategoryItem
import com.example.banksampah.model.SampahKategoryResponse
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class PlastikViewModel : BaseViewModel() {

    companion object {
        const val ACTION_PLASTIK_TIMEOUT = "action_kertas_timeout"
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
                    "58e019d30df34f88627ea346a9aab8fe"
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
                    action.postValue(ACTION_PLASTIK_TIMEOUT)
                }
            }
        }
    }

}