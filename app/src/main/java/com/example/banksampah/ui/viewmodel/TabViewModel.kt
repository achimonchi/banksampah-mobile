package com.example.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.SampahKategoryItem
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_KERTAS
import com.example.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_LAIN
import com.example.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_LOGAM
import com.example.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_PLASTIK
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class TabViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_KERTAS_TIMEOUT = "action_kertas_timeout"
        const val ACTION_ITEM_UPDATE = "actoin_item_update"
    }

    var type = ""
    val list = ArrayList<SampahKategoryItem>()
    val actionItemClick = MutableLiveData<Int>()
    val isItemClicked = MutableLiveData<Boolean>()

    fun setGrid() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response =
                repository.getSampahByCategory(Session.token ?: "", getTypeById(type))) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)

                    list.clear()
                    response.data?.data?.forEach { item ->
                        item?.let { list.add(it) }
                    }

                    action.postValue(ACTION_ITEM_UPDATE)
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_KERTAS_TIMEOUT)
                }
            }
        }
    }

    fun onClickItem(position: Int) {
        actionItemClick.value = position
        isItemClicked.value = false
    }

    private fun getTypeById(type: String): String {
        return when (type) {
            TYPE_KERTAS -> "272ce4ad7c522801c2e06b5ce8b65496"
            TYPE_LOGAM -> "a9f9ad04853e5a154256ecc2a2b0009a"
            TYPE_PLASTIK -> "58e019d30df34f88627ea346a9aab8fe"
            TYPE_LAIN -> "3c82981a5af24542ca9771ca9175877f"
            else -> ""
        }
    }

}