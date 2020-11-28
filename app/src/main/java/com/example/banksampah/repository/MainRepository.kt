package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.*
import com.example.banksampah.utill.Resource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class MainRepository {

    suspend fun authLogin(auth: Auth): Resource<AuthResponse> {
        val response = RetrofitInstance.api.authLogin(auth)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun authSignUp(auth: Auth): Resource<AuthResponse> {
        val response = RetrofitInstance.api.authSignup(auth)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getNasabah(token: String): Resource<Nasabah> {
        val response = RetrofitInstance.api.getNasabah(token)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun updateNasabah(token: String, nasabah: NasabahUpdate): Resource<NasabahResponse> {
        val response = RetrofitInstance.api.updateNasabah(token, nasabah)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getSampahCategory(token: String): Resource<SampahResponse> {
        val response = RetrofitInstance.api.getSampahCategory(token)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getSampahByCategory(
        token: String,
        id: String
    ): Resource<SampahKategoryResponse> {
        val response = RetrofitInstance.api.getSampahByCategory(token, id)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun requestSampah(
        idGarbage: String,
        rWeight: String,
        rImage: String,
        rNotes: String
    ): Resource<RequestSampahResponse> {
        val response = RetrofitInstance.api.requestSampah(
            idGarbage.toRequestBody("text/plain".toMediaTypeOrNull()),
            rWeight.toRequestBody("text/plain".toMediaTypeOrNull()),
            rImage.toRequestBody("image/*".toMediaTypeOrNull()),
            rNotes.toRequestBody("text/plain".toMediaTypeOrNull())
        )

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

}