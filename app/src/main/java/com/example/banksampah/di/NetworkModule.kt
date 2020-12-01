package com.example.banksampah.di

import com.example.banksampah.BuildConfig
import com.example.banksampah.api.ApiService
import com.example.banksampah.api.helper.AuthHelper
import com.example.banksampah.api.helper.NasabahHelper
import com.example.banksampah.api.helper.RequestSampahHelper
import com.example.banksampah.api.helper.SampahHelper
import com.example.banksampah.api.implementation.AuthImpl
import com.example.banksampah.api.implementation.NasabahImpl
import com.example.banksampah.api.implementation.RequestSampahImpl
import com.example.banksampah.api.implementation.SampahImpl
import com.example.banksampah.utill.Constant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthHelper(authHelper: AuthImpl): AuthHelper = authHelper

    @Singleton
    @Provides
    fun provideNasabahHelper(nasabahHelper: NasabahImpl): NasabahHelper = nasabahHelper

    @Singleton
    @Provides
    fun provideSampahHelper(sampahHelper: SampahImpl): SampahHelper = sampahHelper

    @Singleton
    @Provides
    fun provideRequestSampahHelper(requestSampahHelper: RequestSampahImpl): RequestSampahHelper =
        requestSampahHelper

}