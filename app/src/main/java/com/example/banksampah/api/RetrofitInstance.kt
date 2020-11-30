package com.example.banksampah.api

import com.example.banksampah.api.service.AuthService
import com.example.banksampah.api.service.NasabahService
import com.example.banksampah.api.service.RequestSampahService
import com.example.banksampah.api.service.SampahService
import com.example.banksampah.utill.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val auth by lazy {
        retrofit.create(AuthService::class.java)
    }

    val nasabah by lazy {
        retrofit.create(NasabahService::class.java)
    }

    val sampah by lazy {
        retrofit.create(SampahService::class.java)
    }

    val requestSampah by lazy {
        retrofit.create(RequestSampahService::class.java)
    }

}