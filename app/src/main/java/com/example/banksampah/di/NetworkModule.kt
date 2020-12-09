package com.example.banksampah.di

import com.example.banksampah.BuildConfig
import com.example.banksampah.api.AuthService
import com.example.banksampah.api.NasabahService
import com.example.banksampah.api.RequestSampahService
import com.example.banksampah.api.SampahService
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
    fun provideAuthService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideNasabahService(retrofit: Retrofit) = retrofit.create(NasabahService::class.java)

    @Singleton
    @Provides
    fun provideRequestSampahService(retrofit: Retrofit) =
        retrofit.create(RequestSampahService::class.java)

    @Singleton
    @Provides
    fun provideSampahService(retrofit: Retrofit) = retrofit.create(SampahService::class.java)

}