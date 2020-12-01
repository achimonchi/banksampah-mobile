package com.example.banksampah.api.helper

import com.example.banksampah.model.entity.AuthItem
import com.example.banksampah.model.response.AuthResponse
import retrofit2.Response

interface AuthHelper {

    suspend fun authLogin(
        authItem: AuthItem.Data
    ): Response<AuthResponse>

    suspend fun authSignup(
        authItem: AuthItem.Data
    ): Response<AuthResponse>

}