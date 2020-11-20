package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.Auth
import com.example.banksampah.utill.Resource
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {

    companion object {
        const val ACTION_REGISTER_SUCCESSFULLY = "action_register_successfully"
        const val ACTION_REGISTER_FAILED = "action_register_failed"
        const val ACTION_REGISTER_TIMEOUT = "action_register_timeout"
        const val ACTION_REGISTER_PASSWORD_NOT_MATCH = "action_register_password_not_match"
    }

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirm = MutableLiveData<String>()

    fun register() {
        loadingEnabled.value = true
        if (password.value ?: "" == passwordConfirm.value ?: "") {
            viewModelScope.launch {
                when (val result =
                    repository.authSignUp(Auth(email.value ?: "", password.value ?: ""))) {
                    is Resource.Success -> {
                        when (result.data?.status) {
                            200 -> {
                                loadingEnabled.postValue(false)
                                action.postValue(ACTION_REGISTER_SUCCESSFULLY)
                            }
                            401 -> {
                                loadingEnabled.postValue(false)
                                action.postValue(ACTION_REGISTER_FAILED)
                            }
                        }
                    }
                    is Resource.Error -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_REGISTER_TIMEOUT)
                    }
                }
            }
        } else {
            loadingEnabled.value = false
            action.value = ACTION_REGISTER_PASSWORD_NOT_MATCH
        }
    }

}