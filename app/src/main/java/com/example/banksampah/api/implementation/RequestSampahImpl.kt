package com.example.banksampah.api.implementation

import com.example.banksampah.api.ApiService
import com.example.banksampah.api.helper.RequestSampahHelper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RequestSampahImpl @Inject constructor(
    private val apiService: ApiService
) : RequestSampahHelper {

    override suspend fun postRequestSampah(
        token: String,
        id: RequestBody,
        weight: RequestBody,
        image: MultipartBody.Part,
        notes: RequestBody
    ) = apiService.postRequestSampah(token, id, weight, image, notes)

}