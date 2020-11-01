package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.Auth

class MainRepository {

    suspend fun authLogin(auth: Auth) = RetrofitInstance.api.authLogin(auth)

    suspend fun authSignUp(auth: Auth) = RetrofitInstance.api.authSignup(auth)
}