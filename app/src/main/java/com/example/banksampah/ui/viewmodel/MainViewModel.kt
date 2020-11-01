package com.example.banksampah.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.Auth
import com.example.banksampah.model.AuthResponse
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val userLoggedIn: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val userSignIn: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

    fun login(auth: Auth) = viewModelScope.launch {
        userLoggedIn.postValue(Resource.Loading())

        val response = mainRepository.authLogin(auth)

        userLoggedIn.postValue(handleLogin(response))
    }

    fun singup(auth: Auth) = viewModelScope.launch {
        userSignIn.postValue(Resource.Loading())

        val response = mainRepository.authSignUp(auth)

        userSignIn.postValue(handleLogin(response))
    }

    private fun handleLogin(response: Response<AuthResponse>): Resource<AuthResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }

    private fun handleSignUp(response: Response<AuthResponse>): Resource<AuthResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }
}