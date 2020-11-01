package com.example.banksampah.api

import com.example.banksampah.model.Auth
import com.example.banksampah.model.AuthResponse
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
    ) : Response<AuthResponse>

    @GET("nasabah/get_saldo")
    suspend fun getNasabah(
        @Header("email") token: String
    )

}