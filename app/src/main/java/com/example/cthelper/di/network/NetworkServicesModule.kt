package com.example.cthelper.di.network

import com.example.cthelper.remote.api.AuthApiService
import com.example.cthelper.remote.api.TestSearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTestSearchApiService(retrofit: Retrofit): TestSearchApiService {
        return retrofit.create(TestSearchApiService::class.java)
    }
}