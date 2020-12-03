package com.example.banksampah.api

import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.model.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    // Auth Endpoint
    @POST("auth/login")
    suspend fun authLogin(
        @Body authItem: AuthItem.Data
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun authSignup(
        @Body authItem: AuthItem.Data
    ): Response<AuthResponse>

}