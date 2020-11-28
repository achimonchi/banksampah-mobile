package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.ui.adapter.KatalogPagerAdapter
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class KatalogViewModel : BaseViewModel() {

    companion object {
        const val ACTION_KATALOG_NAVIGATEUP = "action_katalog_navigateup"
        const val ACTION_KATALOG_TIMEOUT = "action_katalog_timeout"
    }

    fun navigateUp() {
        action.value = ACTION_KATALOG_NAVIGATEUP
    }

    fun setTitle(adapter: KatalogPagerAdapter) {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response = repository.getSampahCategory(Session.token ?: "")) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)
                    response.data?.data?.let {
                        val listTitle: ArrayList<SampahResponse.Data?>? = ArrayList()
                        listTitle?.addAll(it)

                        adapter.listTab = listTitle
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_KATALOG_TIMEOUT)
                }
            }
        }
    }

}