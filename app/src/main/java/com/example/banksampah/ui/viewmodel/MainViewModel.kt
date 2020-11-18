package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.*
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val userLoggedIn: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val userSignIn: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val userData: MutableLiveData<Resource<Nasabah>> = MutableLiveData()
    val nasabahUpdate: MutableLiveData<Resource<NasabahResponse>> = MutableLiveData()
    val isVerified: MutableLiveData<Boolean> = MutableLiveData()

    fun login(auth: Auth) = viewModelScope.launch {
        userLoggedIn.postValue(Resource.Loading())

        val response = mainRepository.authLogin(auth)

        userLoggedIn.postValue(handleAuth(response))
    }

    fun signup(auth: Auth) = viewModelScope.launch {
        userSignIn.postValue(Resource.Loading())

        val response = mainRepository.authSignUp(auth)

        userSignIn.postValue(handleAuth(response))
    }

    fun getNasabah(token: String) = viewModelScope.launch {
        userData.postValue(Resource.Loading())

        val response = mainRepository.getNasabah(token)

        userData.postValue(handleNasabah(response))
    }

    fun updateNasabah(token: String, nasabah: NasabahUpdate) = viewModelScope.launch {
        nasabahUpdate.postValue(Resource.Loading())

        val response = mainRepository.updateNasabah(token, nasabah)

        nasabahUpdate.postValue(handleUpdateNasabah(response))
    }



    private fun handleAuth(response: Response<AuthResponse>): Resource<AuthResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }

    private fun handleNasabah(response: Response<Nasabah>): Resource<Nasabah> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                isVerified.postValue((res.isExist?.toInt()!! > 0))
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }

    private fun handleUpdateNasabah(response: Response<NasabahResponse>): Resource<NasabahResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }



}