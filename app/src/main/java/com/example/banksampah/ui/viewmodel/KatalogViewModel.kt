package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.utill.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class KatalogViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val sampahCategory: MutableLiveData<Resource<SampahResponse>> = MutableLiveData()

    fun getSampahCategory(token: String) = viewModelScope.launch {
        sampahCategory.postValue(Resource.Loading())

        val response = mainRepository.getSampahCategory(token)

        sampahCategory.postValue(handleGetSampahCategory(response))
    }

    private fun handleGetSampahCategory(response: Response<SampahResponse>): Resource<SampahResponse> {
        if (response.isSuccessful)
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        return Resource.Error(response.message())
    }

}