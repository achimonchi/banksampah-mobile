package com.example.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_LOGIN_VERIFIED = "action_login_verified"
        const val ACTION_LOGIN_UNVERIFIED = "action_login_unverified"
        const val ACTION_PASSWORD_INVALID = "action_password_invalid"
        const val ACTION_CONNECTION_TIMEOUT = "action_connection_timeout"
        const val ACTION_LOGGED_IN = "action_logged_in"
        const val ACTION_BTN_REGISTER = "action_btn_register"
    }

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        loadingEnabled.value = true
        viewModelScope.launch {
            when (val result =
                repository.authLogin(AuthItem.Data(username.value ?: "", password.value ?: ""))) {
                is Resource.Success -> {
                    when (result.data?.status) {
                        200 -> {
                            Session.token = result.data.data?.token
                            getProfile()
                        }
                        401 -> {
                            loadingEnabled.postValue(false)
                            action.postValue(ACTION_PASSWORD_INVALID)
                        }
                    }
                }
                is Resource.Error -> {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_CONNECTION_TIMEOUT)
                }
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {

            when (val result = repository.getNasabah(Session.token ?: "")) {
                is Resource.Success -> {
                    if (result.data?.isExist == "1") {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_LOGIN_VERIFIED)
                    } else {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_LOGIN_UNVERIFIED)
                    }
                }
                is Resource.Error -> {
                    action.postValue(ACTION_CONNECTION_TIMEOUT)
                }
            }
        }
    }

    fun checkSession() {
        if (Session.token != "") {
            viewModelScope.launch {
                when (val result = repository.getNasabah(Session.token ?: "")) {
                    is Resource.Success -> {
                        if (result.data?.isExist == "1") {
                            action.postValue(ACTION_LOGGED_IN)
                        }
                    }
                    is Resource.Error -> {
                        action.postValue(ACTION_CONNECTION_TIMEOUT)
                    }
                }
            }
        }
    }

    fun tvRegisterOnClick() {
        action.postValue(ACTION_BTN_REGISTER)
    }

}