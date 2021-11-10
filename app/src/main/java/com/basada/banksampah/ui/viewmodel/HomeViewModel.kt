package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.ArtikelResponseItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class HomeViewModel @ViewModelInject constructor(
    val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_HOME_KTLG_KERTAS = "action_home_ktlg_kertas"
        const val ACTION_HOME_KTLG_LOGAM = "action_home_ktlg_logam"
        const val ACTION_HOME_KTLG_PLASTIK = "action_home_ktlg_plastik"
        const val ACTION_HOME_KTLG_LAINNYA = "acition_home_ktlg_lainnya"
        const val ACTION_HOME_JUALSAMPAH = "action_home_jualsampah"
        const val ACTION_HOME_SALDO_ONCLICK = "action_home_saldo_onclick"
        const val ACTION_HOME_LAINNYA_ONCLICK = "action_home_lainnya_onclick"
        const val ACTION_HOME_PICKUP_ONCLICK = "action_home_pickup_onclick"
        const val ACTION_HOME_DROP_ONCLICK = "action_home_drop_onclick"
        const val ACTION_HOME_TIMEOUT = "action_home_timeout"
        const val ACTION_HOME_ITEMUPDATE = "action_home_itemupdate"
        const val ACTION_HOME_ITEMONCLICK = "action_home_itemonclick"
    }

    val listArtikel = ArrayList<ArtikelResponseItem>()
    val actionItemPosition = MutableLiveData<Int>()

    fun setGrid() {
        loadingEnabled.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = repository.getAllArtikel(Session.token ?: "")) {
                    is Resource.Success -> {
                        loadingEnabled.postValue(false)

                        listArtikel.clear()
                        response.data?.data?.forEach { item ->
                            listArtikel.add(item)
                        }

                        action.postValue(ACTION_HOME_ITEMUPDATE)
                    }
                    is Resource.Error -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_HOME_TIMEOUT)
                    }
                }
            }catch (e: SocketTimeoutException) {
                action.postValue(ACTION_HOME_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun itemArtikelOnClick(position: Int) {
        actionItemPosition.value = position
        action.value = ACTION_HOME_ITEMONCLICK
    }

    fun jualSampahOnClick() {
        action.value = ACTION_HOME_JUALSAMPAH
    }

    fun saldoOnClick() {
        action.value = ACTION_HOME_SALDO_ONCLICK
    }

    fun lainnyaOnClick() {
        action.value = ACTION_HOME_LAINNYA_ONCLICK
    }

    fun pickupOnclick() {
        action.value = ACTION_HOME_PICKUP_ONCLICK
    }

    fun dropPointOnClick() {
        action.value = ACTION_HOME_DROP_ONCLICK
    }

    fun katalogKertas() {
        action.value = ACTION_HOME_KTLG_KERTAS
    }

    fun katalogLogam() {
        action.value = ACTION_HOME_KTLG_LOGAM
    }

    fun katalogPlastik() {
        action.value = ACTION_HOME_KTLG_PLASTIK
    }

    fun katalogLainnya() {
        action.value = ACTION_HOME_KTLG_LAINNYA
    }

}