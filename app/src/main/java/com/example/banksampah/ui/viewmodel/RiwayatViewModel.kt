package com.example.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.GetRequestSampahResponseItem
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class RiwayatViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_RIWAYAT_UPDATE = "riwayat_action_update"
        const val ACTION_RIWAYAT_TIMEOUT = "action_riwayat_timeout"
    }

    val list = ArrayList<GetRequestSampahResponseItem>()

    fun setList() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response = repository.getRequestSampah(Session.token ?: "")) {
                is Resource.Success -> {
                    loadingEnabled.value = false

                    list.clear()
                    response.data?.forEach { item ->
                        list.add(item)
                    }

                    action.postValue(ACTION_RIWAYAT_UPDATE)
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_RIWAYAT_TIMEOUT)
                }
            }
        }
    }

}