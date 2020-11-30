package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.model.response.*
import com.example.banksampah.utill.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class MainRepository {

    suspend fun authLogin(authItem: AuthItem.Data): Resource<AuthResponse> {
        val response = RetrofitInstance.auth.authLogin(authItem)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun authSignUp(authItem: AuthItem.Data): Resource<AuthResponse> {
        val response = RetrofitInstance.auth.authSignup(authItem)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getNasabah(token: String): Resource<NasabahItem> {
        val response = RetrofitInstance.nasabah.getNasabah(token)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it, response.message())
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun updateNasabah(token: String, nasabahItem: NasabahItem): Resource<NasabahResponse> {
        val response = RetrofitInstance.nasabah.updateNasabah(token, nasabahItem)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getSampahCategory(token: String): Resource<SampahResponse> {
        val response = RetrofitInstance.sampah.getSampahCategory(token)

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
        val response = RetrofitInstance.sampah.getSampahByCategory(token, id)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun requestSampah(
        token: String,
        idGarbage: String,
        rWeight: String,
        rImage: File,
        rNotes: String
    ): Resource<RequestSampahResponse> {
        val requestFile = rImage.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        val response = RetrofitInstance.requestSampah.postRequestSampah(
            token,
            idGarbage.toRequestBody("text/plain".toMediaTypeOrNull()),
            rWeight.toRequestBody("text/plain".toMediaTypeOrNull()),
            MultipartBody.Part.createFormData("r_image", rImage.name, requestFile),
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