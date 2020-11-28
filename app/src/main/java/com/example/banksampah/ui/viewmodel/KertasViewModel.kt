package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.SampahKategoryItem
import com.example.banksampah.model.SampahKategoryResponse
import com.example.banksampah.ui.adapter.RecyclerViewAdapter
import com.example.banksampah.ui.fragment.tab.TabKatalog.Companion.TYPE_KERTAS
import com.example.banksampah.ui.fragment.tab.TabKatalog.Companion.TYPE_LOGAM
import com.example.banksampah.ui.fragment.tab.TabKatalog.Companion.TYPE_PLASTIK
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class KertasViewModel : BaseViewModel() {

    companion object {
        const val ACTION_KERTAS_TIMEOUT = "action_kertas_timeout"
        const val ACTION_ITEM_UPDATE = "actoin_item_update"
    }

    var type = ""
    val list = ArrayList<SampahKategoryItem>()
    val actionItemClick = MutableLiveData<Int>()

    fun setGrid() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response =
                repository.getSampahByCategory(
                    Session.token ?: "",
                    getTypeById(type)
                )) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_ITEM_UPDATE)
                    list.clear()
                    response.data?.data?.forEach {
                        it?.let {
                            list.add(it)
                        }
                    }
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
    }

    private fun getTypeById(type: String): String {
        return when (type) {
            TYPE_KERTAS -> "272ce4ad7c522801c2e06b5ce8b65496"
            TYPE_LOGAM -> "a9f9ad04853e5a154256ecc2a2b0009a"
            TYPE_PLASTIK -> "58e019d30df34f88627ea346a9aab8fe"
            else -> "3c82981a5af24542ca9771ca9175877f"
        }
    }

}