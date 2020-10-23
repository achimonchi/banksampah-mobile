package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance.Companion.api
import com.example.banksampah.model.Auth

class MainRepository {

    suspend fun authLogin(auth: Auth) = api.authLogin(auth)

}