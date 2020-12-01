package com.example.banksampah.api.helper

import com.example.banksampah.model.response.RequestSampahResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface RequestSampahHelper {

    suspend fun postRequestSampah(
        token: String,
        id: RequestBody,
        weight: RequestBody,
        image: MultipartBody.Part,
        notes: RequestBody
    ): Response<RequestSampahResponse>

}