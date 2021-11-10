package com.basada.banksampah.api

import com.basada.banksampah.model.response.GetRequestSampahResponse
import com.basada.banksampah.model.response.RequestAdminSampah
import com.basada.banksampah.model.response.RequestSampahResponse
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
        @Part("fk_jadwal") fkJadwal: RequestBody,
        @Part("fk_admin") fkAdmin: RequestBody,
        @Part("r_notes") notes: RequestBody
    ): Response<RequestSampahResponse>

    @GET("requestSampah/get_request")
    suspend fun getRequestSampah(
        @Header("token") token: String
    ): Response<GetRequestSampahResponse>

    @GET("requestSampah/get_min_sampah")
    suspend fun getMinSampah(
        @Header("token") token: String
    ): Response<String>

    @GET("requestSampah/get_admin")
    suspend fun getAdmin(
        @Header("token") token: String
    ): Response<RequestAdminSampah>

}