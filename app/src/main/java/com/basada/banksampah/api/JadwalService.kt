package com.basada.banksampah.api

import com.basada.banksampah.model.response.JadwalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface JadwalService {

    @GET("jadwal/get_jadwal")
    suspend fun getAllJadwal(
        @Header("token") token: String
    ): Response<JadwalResponse>

}