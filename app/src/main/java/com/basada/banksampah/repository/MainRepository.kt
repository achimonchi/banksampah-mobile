package com.basada.banksampah.repository

import com.basada.banksampah.api.*
import com.basada.banksampah.model.entity.AuthItem
import com.basada.banksampah.model.entity.ChangePasswordItem
import com.basada.banksampah.model.entity.NasabahItem
import com.basada.banksampah.model.response.*
import com.basada.banksampah.utill.Resource
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
    private val requestSampahService: RequestSampahService,
    private val artikelService: ArtikelService,
    private val jadwalService: JadwalService
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
        fkJadwal: String,
        fkAdmin: String,
        rNotes: String
    ): Resource<RequestSampahResponse> {
        val requestFile = rImage.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        requestSampahService.postRequestSampah(
            token,
            idGarbage.toRequestBody("text/plain".toMediaTypeOrNull()),
            rWeight.toRequestBody("text/plain".toMediaTypeOrNull()),
            MultipartBody.Part.createFormData("r_image", rImage.name, requestFile),
            fkJadwal.toRequestBody("text/plain".toMediaTypeOrNull()),
            fkAdmin.toRequestBody("text/plain".toMediaTypeOrNull()),
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

    suspend fun getMinSampah(token: String): Resource<String> {
        requestSampahService.getMinSampah(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let { return Resource.Success(it) }
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

    suspend fun getAdminInduk(token: String): Resource<RequestAdminSampah> {
        requestSampahService.getAdmin(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getJadwalSampah(token: String): Resource<JadwalResponse> {
        jadwalService.getAllJadwal(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getAllArtikel(token: String): Resource<ArtikelResponse> {
        artikelService.getAllArtikel(token).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun changePassword(
        token: String,
        changePasswordItem: ChangePasswordItem
    ): Resource<ChangePasswrodResponse> {
        nasabahService.changePassword(token, changePasswordItem).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

}