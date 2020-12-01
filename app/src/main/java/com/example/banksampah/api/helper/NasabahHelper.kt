package com.example.banksampah.api.helper

import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.model.response.NasabahResponse
import retrofit2.Response

interface NasabahHelper {

    suspend fun getNasabah(
        token: String
    ): Response<NasabahItem>

    suspend fun updateNasabah(
        token: String,
        nasabahItem: NasabahItem
    ): Response<NasabahResponse>

}