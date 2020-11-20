package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.*
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val userData: MutableLiveData<Resource<Nasabah>> = MutableLiveData()
    val nasabahUpdate: MutableLiveData<Resource<NasabahResponse>> = MutableLiveData()

    val nasabah = MutableLiveData<Resource<Nasabah>>()

    fun getNasabah() {
        viewModelScope.launch {
            val response = repository.getNasabah(Session.token ?: "")
            nasabah.postValue(response)
        }
    }

    fun updateNasabah(token: String, nasabah: NasabahUpdate) = viewModelScope.launch {
        nasabahUpdate.postValue(Resource.Loading())

        val response = repository.updateNasabah(token, nasabah)

        nasabahUpdate.postValue(handleUpdateNasabah(response))
    }

    private fun handleUpdateNasabah(response: Response<NasabahResponse>): Resource<NasabahResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }

}