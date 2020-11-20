package com.example.banksampah.repository

import com.example.banksampah.api.RetrofitInstance
import com.example.banksampah.model.Auth
import com.example.banksampah.model.AuthResponse
import com.example.banksampah.model.Nasabah
import com.example.banksampah.model.NasabahUpdate
import com.example.banksampah.utill.Resource

class MainRepository {

    suspend fun authLogin(auth: Auth): Resource<AuthResponse> {
        val response = RetrofitInstance.api.authLogin(auth)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun authSignUp(auth: Auth): Resource<AuthResponse> {
        val response = RetrofitInstance.api.authSignup(auth)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun getNasabah(token: String): Resource<Nasabah> {
        val response = RetrofitInstance.api.getNasabah(token)

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    suspend fun updateNasabah(token: String, nasabah: NasabahUpdate) =
        RetrofitInstance.api.updateNasabah(token, nasabah)

    suspend fun getSampahCategory(token: String) = RetrofitInstance.api.getSampahCategory(token)

}