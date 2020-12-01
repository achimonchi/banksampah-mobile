package com.example.banksampah.api.implementation

import com.example.banksampah.api.ApiService
import com.example.banksampah.api.helper.NasabahHelper
import com.example.banksampah.model.entity.NasabahItem
import javax.inject.Inject

class NasabahImpl @Inject constructor(
    private val apiService: ApiService
) : NasabahHelper {

    override suspend fun getNasabah(token: String) = apiService.getNasabah(token)

    override suspend fun updateNasabah(token: String, nasabahItem: NasabahItem) =
        apiService.updateNasabah(token, nasabahItem)
}