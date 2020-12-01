package com.example.banksampah.api

import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.model.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth Endpoint
    @POST("auth/login")
    suspend fun authLogin(
        @Body authItem: AuthItem.Data
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun authSignup(
        @Body authItem: AuthItem.Data
    ): Response<AuthResponse>

    // nasabah Endpoint
    @GET("nasabah/get_nasabah")
    suspend fun getNasabah(
        @Header("token") token: String
    ): Response<NasabahItem>

    @POST("nasabah/update_nasabah")
    suspend fun updateNasabah(
        @Header("token") token: String,
        @Body nasabahItem: NasabahItem
    ): Response<NasabahResponse>

    // sampah Endpoint
    @GET("sampah/get_kategori")
    suspend fun getSampahCategory(
        @Header("token") token: String
    ): Response<SampahResponse>

    @GET("sampah/get_sampah_by_kategori/{id}")
    suspend fun getSampahByCategory(
        @Header("token") token: String,
        @Path("id") id: String
    ): Response<SampahKategoryResponse>

    // requestsampah Endpoint
    @Multipart
    @POST("requestsampah/request")
    suspend fun postRequestSampah(
        @Header("token") token: String,
        @Part("fk_garbage") id: RequestBody,
        @Part("r_weight") weight: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("r_notes") notes: RequestBody
    ): Response<RequestSampahResponse>

}