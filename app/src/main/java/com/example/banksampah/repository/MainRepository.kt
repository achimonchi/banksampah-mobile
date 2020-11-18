package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.Auth
import com.example.banksampah.model.NasabahUpdate

class MainRepository {

    suspend fun authLogin(auth: Auth) = RetrofitInstance.api.authLogin(auth)

    suspend fun authSignUp(auth: Auth) = RetrofitInstance.api.authSignup(auth)

    suspend fun getNasabah(token: String) = RetrofitInstance.api.getNasabah(token)

    suspend fun updateNasabah(token: String, nasabah: NasabahUpdate) =
        RetrofitInstance.api.updateNasabah(token, nasabah)

    suspend fun getSampahCategory(token: String) = RetrofitInstance.api.getSampahCategory(token)

}