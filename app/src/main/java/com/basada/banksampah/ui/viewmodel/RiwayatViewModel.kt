package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.GetRequestSampahResponseItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class RiwayatViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_RIWAYAT_UPDATE = "riwayat_action_update"
        const val ACTION_RIWAYAT_TIMEOUT = "action_riwayat_timeout"
    }

    val list = ArrayList<GetRequestSampahResponseItem>()
    val areListAvailable = MutableLiveData<Boolean>()

    fun setList() {
        loadingEnabled.value = true
        areListAvailable.value = true

        viewModelScope.launch {
            try {
                when (val response = repository.getRequestSampah(Session.token ?: "")) {
                    is Resource.Success -> {
                        loadingEnabled.value = false

                        list.clear()

                        response.data?.forEach { item ->
                            list.add(item)
                        }

                        if (list.size != 0) {
                            areListAvailable.postValue(false)
                        }

                        action.postValue(ACTION_RIWAYAT_UPDATE)
                    }
                    is Resource.Error -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_RIWAYAT_TIMEOUT)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_RIWAYAT_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
