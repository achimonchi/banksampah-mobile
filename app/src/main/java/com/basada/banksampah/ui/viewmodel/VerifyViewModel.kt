package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.NasabahItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import com.basada.banksampah.utill.Utill
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class VerifyViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_VERIFY_SUCCESS = "action_verify_success"
        const val ACTION_VERIFY_TIMEOUT = "action_verify_timeout"
        const val ACTION_VERIFY_DATEONCLICK = "action_verify_dateonclick"
        const val ACTION_VERIFY_FORM_EMPTY = "action_verify_form_empty"
    }

    val fullNameText = MutableLiveData<String>()
    val phoneNumberText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val cityText = MutableLiveData<String>()
    val provinceText = MutableLiveData<String>()
    val postcodeText = MutableLiveData<String>()
    val dateOfBirth = MutableLiveData<String>()

    fun verify() {
        loadingEnabled.value = true

        if (Utill.isTextNotEmpty(
                fullNameText.value ?: "",
                phoneNumberText.value ?: "",
                addressText.value ?: "",
                cityText.value ?: "",
                provinceText.value ?: "",
                postcodeText.value ?: "",
                dateOfBirth.value ?: ""
            )
        ) {
            viewModelScope.launch {
                try {
                    when (repository.updateNasabah(
                        Session.token ?: "",
                        NasabahItem(
                            nName = fullNameText.value,
                            nContact = phoneNumberText.value,
                            nAddress = addressText.value,
                            nCity = cityText.value,
                            nProvince = provinceText.value,
                            nPostcode = postcodeText.value,
                            nDob = dateOfBirth.value
                        )
                    )) {
                        is Resource.Success<*> -> {
                            loadingEnabled.postValue(false)
                            action.postValue(ACTION_VERIFY_SUCCESS)
                        }
                        is Resource.Error<*> -> {
                            loadingEnabled.postValue(false)
                            action.postValue(ACTION_VERIFY_TIMEOUT)
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    action.postValue(ACTION_VERIFY_TIMEOUT)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            action.value = ACTION_VERIFY_FORM_EMPTY
            loadingEnabled.value = false
        }
    }

    fun dateOfBirthOnClick() {
        action.value = ACTION_VERIFY_DATEONCLICK
    }

}