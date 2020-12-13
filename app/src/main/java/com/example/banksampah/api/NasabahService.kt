package com.example.banksampah.api

import com.example.banksampah.model.entity.NasabahItem
import com.example.banksampah.model.response.NasabahResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NasabahService {

    @GET("nasabah/get_nasabah")
    suspend fun getNasabah(
        @Header("token") token: String
    ): Response<NasabahItem>

    @POST("nasabah/update_nasabah")
    suspend fun updateNasabah(
        @Header("token") token: String,
        @Body nasabahItem: NasabahItem
    ): Response<NasabahResponse>

}