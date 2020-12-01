package com.example.banksampah.api.helper

import com.example.banksampah.model.response.SampahKategoryResponse
import com.example.banksampah.model.response.SampahResponse
import retrofit2.Response

interface SampahHelper {

    suspend fun getSampahCategory(
        token: String
    ): Response<SampahResponse>

    suspend fun getSampahByCategory(
        token: String,
        id: String
    ): Response<SampahKategoryResponse>

}