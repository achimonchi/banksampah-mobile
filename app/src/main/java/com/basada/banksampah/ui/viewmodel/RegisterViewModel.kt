package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.AuthItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class RegisterViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_REGISTER_SUCCESSFULLY = "action_register_successfully"
        const val ACTION_REGISTER_FAILED = "action_register_failed"
        const val ACTION_REGISTER_TIMEOUT = "action_register_timeout"
        const val ACTION_REGISTER_PASSWORD_NOT_MATCH = "action_register_password_not_match"
        const val ACTION_REGISTER_ONCLICK_LOGIN = "action_register_onclick_login"
    }

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirm = MutableLiveData<String>()

    var message = ""

    fun register() {
        loadingEnabled.value = true
        if (password.value ?: "" == passwordConfirm.value ?: "") {
            viewModelScope.launch {
                try {
                    when (val result =
                        repository.authSignUp(
                            AuthItem.Data(
                                email.value ?: "",
                                password.value ?: ""
                            )
                        )) {
                        is Resource.Success -> {
                            when (result.data?.status) {
                                200 -> {
                                    loadingEnabled.postValue(false)
                                    action.postValue(ACTION_REGISTER_SUCCESSFULLY)
                                }
                                401 -> {
                                    loadingEnabled.postValue(false)
                                    message = "Email Sudah dipakai"
                                    action.postValue(ACTION_REGISTER_FAILED)
                                }
                            }
                        }
                        is Resource.Error -> {
                            loadingEnabled.postValue(false)
                            message = result.message.toString()
                            action.postValue(ACTION_REGISTER_TIMEOUT)
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    action.postValue(ACTION_REGISTER_TIMEOUT)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            loadingEnabled.value = false
            action.value = ACTION_REGISTER_PASSWORD_NOT_MATCH
        }
    }

    fun onClickLogin() {
        action.value = ACTION_REGISTER_ONCLICK_LOGIN
    }

}