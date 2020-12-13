package com.example.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class VerifyViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_VERIFY_SUCCESS = "action_verify_success"
        const val ACTION_VERIFY_TIMEOUT = "action_verify_timeout"
    }

    val fullNameText = MutableLiveData<String>()
    val phoneNumberText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val cityText = MutableLiveData<String>()
    val provinceText = MutableLiveData<String>()
    val postcodeText = MutableLiveData<String>()

    fun verify() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (repository.updateNasabah(
                Session.token ?: "",
                NasabahItem(
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
                    action.postValue(ACTION_VERIFY_SUCCESS)
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_VERIFY_TIMEOUT)
                }
            }
        }
    }


}