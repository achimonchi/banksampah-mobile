package com.example.banksampah.repository

import com.example.banksampah.api.helper.AuthHelper
import com.example.banksampah.api.helper.NasabahHelper
import com.example.banksampah.api.helper.RequestSampahHelper
import com.example.banksampah.api.helper.SampahHelper
import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.model.response.*
import com.example.banksampah.utill.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val authHelper: AuthHelper,
    private val nasabahHelper: NasabahHelper,
    private val sampahHelper: SampahHelper,
    private val requestSampahHelper: RequestSampahHelper
) {

    suspend fun authLogin(authItem: AuthItem.Data): Resource<AuthResponse> {
        authHelper.authLogin(authItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun authSignUp(authItem: AuthItem.Data): Resource<AuthResponse> {
        authHelper.authSignup(authItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getNasabah(token: String): Resource<NasabahItem> {
        nasabahHelper.getNasabah(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun updateNasabah(token: String, nasabahItem: NasabahItem): Resource<NasabahResponse> {
        nasabahHelper.updateNasabah(token, nasabahItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getSampahCategory(token: String): Resource<SampahResponse> {
        sampahHelper.getSampahCategory(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getSampahByCategory(
        token: String,
        id: String
    ): Resource<SampahKategoryResponse> {
        sampahHelper.getSampahByCategory(token, id).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun requestSampah(
        token: String,
        idGarbage: String,
        rWeight: String,
        rImage: File,
        rNotes: String
    ): Resource<RequestSampahResponse> {
        val requestFile = rImage.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        requestSampahHelper.postRequestSampah(
            token,
            idGarbage.toRequestBody("text/plain".toMediaTypeOrNull()),
            rWeight.toRequestBody("text/plain".toMediaTypeOrNull()),
            MultipartBody.Part.createFormData("r_image", rImage.name, requestFile),
            rNotes.toRequestBody("text/plain".toMediaTypeOrNull())
        ).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

}