package com.example.banksampah.api

import com.example.banksampah.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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

}