package com.basada.banksampah.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.SampahKategoryItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import com.basada.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_KERTAS
import com.basada.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_LAIN
import com.basada.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_LOGAM
import com.basada.banksampah.ui.fragment.tab.TabJualSampahFragment.Companion.TYPE_PLASTIK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class TabViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_KERTAS_TIMEOUT = "action_kertas_timeout"
        const val ACTION_ITEM_UPDATE = "actoin_item_update"
        const val ACTION_ITEM_ONCLICK = "action_item_onclick"
    }

    var type = ""
    val list = ArrayList<SampahKategoryItem>()
    val actionItemClick = MutableLiveData<Int>()

    fun setGrid() {
        loadingEnabled.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
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
                    is Resource.Error<*> -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_KERTAS_TIMEOUT)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_KERTAS_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onClickItem(position: Int) {
        actionItemClick.value = position
        action.value = ACTION_ITEM_ONCLICK
    }

    private fun getTypeById(type: String): String {
        return when (type) {
            TYPE_KERTAS -> "ea6988222a32f1af2ac2fe1353d661f2"
            TYPE_LOGAM -> "a9f9ad04853e5a154256ecc2a2b0009a"
            TYPE_PLASTIK -> "07435eb38c61f7883c4b66da088c67a2"
            TYPE_LAIN -> "07435eb38c61f7883c4b66da088c67a2"
            else -> ""
        }
    }

}