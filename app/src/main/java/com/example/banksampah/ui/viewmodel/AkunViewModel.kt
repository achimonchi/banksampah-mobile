package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import com.example.banksampah.utill.Utill
import kotlinx.coroutines.launch

class AkunViewModel : BaseViewModel() {

    companion object {
        const val ACTION_AKUN_LOGOUT = "action_akun_logout"
        const val ACTION_AKUN_TIMEOUT = "action_akun_timeout"
        const val ACTION_AKUN_BTN_UBAHCLICK = "action_akun_btn_ubahclick"
    }

    val fullNameText = MutableLiveData<String>()
    val phoneNumberText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val balanceText = MutableLiveData<String>()

    fun ubahProfil() {
        action.value = ACTION_AKUN_BTN_UBAHCLICK
    }

    fun ketentuanLayanan() {

    }

    fun kebijakanPrivasi() {

    }

    fun agentPenjemputan() {

    }

    fun dropPoint() {

    }

    fun logout() {
        action.value = ACTION_AKUN_LOGOUT
        Session.token = ""
    }

    fun setAkun() {
        loadingEnabled.value = true
        viewModelScope.launch {
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
        }
    }

}