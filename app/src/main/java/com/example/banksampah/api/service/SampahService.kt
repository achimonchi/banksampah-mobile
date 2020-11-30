package com.example.banksampah.api.service

import com.example.banksampah.model.response.SampahKategoryResponse
import com.example.banksampah.model.response.SampahResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SampahService {

    @GET("sampah/get_kategori")
    suspend fun getSampahCategory(
        @Header("token") token: String
    ): Response<SampahResponse>

    @GET("sampah/get_sampah_by_kategori/{id}")
    suspend fun getSampahByCategory(
        @Header("token") token: String,
        @Path("id") id: String
    ): Response<SampahKategoryResponse>

}