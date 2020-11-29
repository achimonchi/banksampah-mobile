package com.example.banksampah.api

import com.example.banksampah.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface BanksampahAPI {

    @POST("auth/login")
    suspend fun authLogin(
        @Body auth: Auth
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun authSignup(
        @Body auth: Auth
    ): Response<AuthResponse>

    @GET("nasabah/get_nasabah")
    suspend fun getNasabah(
        @Header("token") token: String
    ): Response<Nasabah>

    @POST("nasabah/update_nasabah")
    suspend fun updateNasabah(
        @Header("token") token: String,
        @Body nasabah: NasabahUpdate
    ): Response<NasabahResponse>

    @GET("sampah/get_kategori")
    suspend fun getSampahCategory(
        @Header("token") token: String
    ): Response<SampahResponse>

    @GET("sampah/get_sampah_by_kategori/{id}")
    suspend fun getSampahByCategory(
        @Header("token") token: String,
        @Path("id") id: String
    ): Response<SampahKategoryResponse>

    @Multipart
    @POST("requestsampah/request")
    suspend fun requestSampah(
        @Header("token") token: String,
        @Part("fk_garbage") id: RequestBody,
        @Part("r_weight") weight: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("r_notes") notes: RequestBody
    ): Response<RequestSampahResponse>

}