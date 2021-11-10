package com.basada.banksampah.api

import com.basada.banksampah.model.response.ArtikelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ArtikelService {

    @GET("artikel/get_limit_artikel/5/1")
    suspend fun getAllArtikel(
        @Header("token") token: String
    ): Response<ArtikelResponse>

}