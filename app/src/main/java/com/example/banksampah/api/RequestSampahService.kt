package com.example.banksampah.api

import com.example.banksampah.model.response.GetRequestSampahResponse
import com.example.banksampah.model.response.RequestSampahResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RequestSampahService {

    @Multipart
    @POST("requestSampah/request")
    suspend fun postRequestSampah(
        @Header("token") token: String,
        @Part("fk_garbage") id: RequestBody,
        @Part("r_weight") weight: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("r_notes") notes: RequestBody
    ): Response<RequestSampahResponse>

    @GET("requestSampah/get_request")
    suspend fun getRequestSampah(
        @Header("token") token: String
    ): Response<GetRequestSampahResponse>

}