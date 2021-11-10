package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import com.basada.banksampah.utill.Utill
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class AkunViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_AKUN_LOGOUT = "action_akun_logout"
        const val ACTION_AKUN_TIMEOUT = "action_akun_timeout"
        const val ACTION_AKUN_BTN_UBAHCLICK = "action_akun_btn_ubahclick"
        const val ACTION_AKUN_DROP_ONCLICK = "action_akun_drop_onclick"
        const val ACTION_AKUN_AGEN_ONCLICK = "action_akun_agen_onclick"
        const val ACTION_AKUN_BANTUAN_ONCLICK = "action_akun_bantuan_onclick"
    }

    val fullNameText = MutableLiveData<String>()
    val phoneNumberText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val balanceText = MutableLiveData<String>()

    fun ubahProfil() {
        action.value = ACTION_AKUN_BTN_UBAHCLICK
    }

    fun bantuan() {
        action.value = ACTION_AKUN_BANTUAN_ONCLICK
    }

    fun agentPenjemputan() {
        action.value = ACTION_AKUN_AGEN_ONCLICK
    }

    fun dropPoint() {
        action.value = ACTION_AKUN_DROP_ONCLICK
    }

    fun logout() {
        action.value = ACTION_AKUN_LOGOUT
        Session.token = ""
    }

    fun setAkun() {
        loadingEnabled.value = true
        viewModelScope.launch {
            try {
                when (val response = repository.getNasabah(Session.token ?: "")) {
                    is Resource.Success -> {
                        loadingEnabled.postValue(false)

                        response.data?.let { nasabah ->
                            fullNameText.postValue(nasabah.nName)
                            phoneNumberText.postValue(nasabah.nContact)
                            emailText.postValue(nasabah.fkAuth)
                            balanceText.postValue(Utill.stringToRp(nasabah.nBalance ?: ""))
                        }

                    }
                    is Resource.Error -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_AKUN_TIMEOUT)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_AKUN_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}