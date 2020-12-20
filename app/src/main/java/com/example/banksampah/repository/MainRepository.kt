package com.example.banksampah.repository

import com.example.banksampah.api.AuthService
import com.example.banksampah.api.NasabahService
import com.example.banksampah.api.RequestSampahService
import com.example.banksampah.api.SampahService
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
    private val authService: AuthService,
    private val nasabahService: NasabahService,
    private val sampahService: SampahService,
    private val requestSampahService: RequestSampahService
) {

    suspend fun authLogin(authItem: AuthItem.Data): Resource<AuthResponse> {
        authService.authLogin(authItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun authSignUp(authItem: AuthItem.Data): Resource<AuthResponse> {
        authService.authSignup(authItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getNasabah(token: String): Resource<NasabahItem> {
        nasabahService.getNasabah(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun updateNasabah(token: String, nasabahItem: NasabahItem): Resource<NasabahResponse> {
        nasabahService.updateNasabah(token, nasabahItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getSampahCategory(token: String): Resource<SampahResponse> {
        sampahService.getSampahCategory(token).let { response ->
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
        sampahService.getSampahByCategory(token, id).let { response ->
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

        requestSampahService.postRequestSampah(
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

    suspend fun getRequestSampah(token: String): Resource<GetRequestSampahResponse> {
        requestSampahService.getRequestSampah(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

}