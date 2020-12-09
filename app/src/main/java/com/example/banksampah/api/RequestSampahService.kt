package com.example.banksampah.api

import com.example.banksampah.model.response.RequestSampahResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RequestSampahService {

    @Multipart
    @POST("requestsampah/request")
    suspend fun postRequestSampah(
        @Header("token") token: String,
        @Part("fk_garbage") id: RequestBody,
        @Part("r_weight") weight: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("r_notes") notes: RequestBody
    ): Response<RequestSampahResponse>

}