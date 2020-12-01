package com.example.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.SampahItem
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class SampahViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_KATALOG_NAVIGATEUP = "action_katalog_navigateup"
        const val ACTION_KATALOG_TIMEOUT = "action_katalog_timeout"
        const val ACTION_ITEM_UPDATE = "action_item_update"
    }

    val listTitle: ArrayList<SampahItem>? = ArrayList()

    fun navigateUp() {
        action.value = ACTION_KATALOG_NAVIGATEUP
    }

    fun setTitle() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response = repository.getSampahCategory(Session.token ?: "")) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)
                    response.data?.data?.forEach { item ->
                        item?.let { listTitle?.add(it) }
                    }
                    action.postValue(ACTION_ITEM_UPDATE)
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_KATALOG_TIMEOUT)
                }
            }
        }
    }

}