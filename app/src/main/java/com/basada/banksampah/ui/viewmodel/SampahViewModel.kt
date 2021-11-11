package com.basada.banksampah.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.SampahItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

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
            try {
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
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_KATALOG_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}