package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}