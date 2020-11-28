package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.NasabahUpdate
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class EditProfileViewModel : BaseViewModel() {

    companion object {
        const val ACTION_EDIT_BTN_PASSCLICK = "action_edit_btn_passclick"
        const val ACTION_EDIT_SUCCESS = "action_edit_success"
        const val ACTION_EDIT_TIMEOUT = "action_edit_timeout"
        const val ACTION_EDIT_NAVIGATEUP = "action_edit_navigateup"
    }

    val fullNameText = MutableLiveData<String>()
    val phoneNumberText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val cityText = MutableLiveData<String>()
    val provinceText = MutableLiveData<String>()
    val postcodeText = MutableLiveData<String>()

    fun navigateUp() {
        action.value = ACTION_EDIT_NAVIGATEUP
    }

    fun ubahPassword() {
        action.value = ACTION_EDIT_BTN_PASSCLICK
    }

    fun setProfile() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response = repository.getNasabah(Session.token ?: "")) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)

                    response.data?.let { nasabah ->
                        fullNameText.postValue(nasabah.nName)
                        phoneNumberText.postValue(nasabah.nContact)
                        addressText.postValue(nasabah.nAddress)
                        cityText.postValue(nasabah.nCity)
                        provinceText.postValue(nasabah.nProvince)
                        postcodeText.postValue(nasabah.nPostcode)
                    }
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_EDIT_TIMEOUT)
                }
            }
        }
    }

    fun updateProfile() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val response = repository.updateNasabah(
                Session.token ?: "",
                NasabahUpdate(
                    nName = fullNameText.value,
                    nContact = phoneNumberText.value,
                    nAddress = addressText.value,
                    nCity = cityText.value,
                    nProvince = provinceText.value,
                    nPostcode = postcodeText.value
                )
            )) {
                is Resource.Success -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_EDIT_SUCCESS)
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_EDIT_TIMEOUT)
                }
            }
        }
    }

}