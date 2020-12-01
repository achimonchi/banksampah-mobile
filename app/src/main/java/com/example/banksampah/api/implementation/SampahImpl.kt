package com.example.banksampah.api.implementation

import com.example.banksampah.api.ApiService
import com.example.banksampah.api.helper.SampahHelper
import javax.inject.Inject

class SampahImpl @Inject constructor(
    private val apiService: ApiService
) : SampahHelper {

    override suspend fun getSampahCategory(token: String) = apiService.getSampahCategory(token)

    override suspend fun getSampahByCategory(token: String, id: String) =
        apiService.getSampahByCategory(token, id)
}