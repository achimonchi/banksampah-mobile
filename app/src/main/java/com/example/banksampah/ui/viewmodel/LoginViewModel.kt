package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banksampah.api.RetrofitInstance.api
import com.example.banksampah.model.Auth
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    companion object {
        const val ACTION_LOGIN_VERIFIED = "action_login_verified"
        const val ACTION_LOGIN_UNVERIFIED = "action_login_unverified"
        const val ACTION_PASSWORD_INVALID = "action_password_invalid"
        const val ACTION_CONNECTION_TIMEOUT = "action_connection_timeout"
        const val ACTION_LOGGED_IN = "action_logged_in"
    }

    val action = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val loadingEnabled = MutableLiveData<Boolean>()

    fun login() {
        loadingEnabled.value = true
        viewModelScope.launch {
            val result = api.authLogin(Auth(username.value ?: "", password.value ?: ""))

            if (result.isSuccessful) {
                Session.token = result.body()?.data?.token

                when (result.body()?.status) {
                    200 -> {
                        getProfile()
                    }
                    401 -> {
                        action.postValue(ACTION_PASSWORD_INVALID)
                    }
                }

            } else {
                loadingEnabled.postValue(false)
                action.postValue(ACTION_CONNECTION_TIMEOUT)
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            val result = api.getNasabah(Session.token ?: "")
            if (result.isSuccessful) {
                if (result.body()?.isExist == "1") {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_LOGIN_VERIFIED)
                } else {
                    loadingEnabled.postValue(false)
                    action.postValue(ACTION_LOGIN_UNVERIFIED)
                }
            }
        }
    }

    fun checkSession() {
        if (Session.token != "") {
            action.postValue(ACTION_LOGGED_IN)
        }
    }

}