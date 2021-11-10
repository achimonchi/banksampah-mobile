package com.basada.banksampah.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.ChangePasswordItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import com.basada.banksampah.utill.Utill
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class EditPasswordViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_EDITPASS_NAVIGATEUP = "action_editpass_navigateup"
        const val ACTION_EDITPASS_FORMEMPTY = "action_editpass_formempty"
        const val ACTION_EDITPASS_TIMEOUT = "action_editpass_timeout"
        const val ACTION_EDITPASS_WRONGPASS = "action_editpass_wrongpass"
        const val ACTION_EDITPASS_PASSCHANGED = "action_editpass_passchanged"
    }

    val passwordLama = MutableLiveData<String>()
    val passwordBaru = MutableLiveData<String>()

    fun navigateUp() {
        action.value = ACTION_EDITPASS_NAVIGATEUP
    }

    fun updatePassword() {
        loadingEnabled.value = true
        if (Utill.isTextNotEmpty(passwordLama.value ?: "", passwordBaru.value ?: "")) {
            viewModelScope.launch {
                try {
                    when (val response = repository.changePassword(
                        Session.token ?: "",
                        ChangePasswordItem(
                            oldPassword = passwordLama.value,
                            newPassword = passwordBaru.value
                        )
                    )) {
                        is Resource.Success -> {
                            when (response.data?.status) {
                                200 -> action.postValue(ACTION_EDITPASS_PASSCHANGED)
                                401 -> action.postValue(ACTION_EDITPASS_WRONGPASS)
                            }
                        }
                        is Resource.Error -> {
                            action.postValue(ACTION_EDITPASS_TIMEOUT)
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    action.postValue(ACTION_EDITPASS_TIMEOUT)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                loadingEnabled.postValue(false)
            }
        } else {
            action.value = ACTION_EDITPASS_FORMEMPTY
        }
    }

}