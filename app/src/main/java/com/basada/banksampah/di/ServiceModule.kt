package com.basada.banksampah.di

import com.basada.banksampah.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

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

    @Singleton
    @Provides
    fun provideArtikelService(retrofit: Retrofit) = retrofit.create(ArtikelService::class.java)

    @Singleton
    @Provides
    fun provideJadwalService(retrofit: Retrofit) = retrofit.create(JadwalService::class.java)

}