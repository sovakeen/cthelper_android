package com.example.cthelper.di.network

import com.example.cthelper.remote.api.AuthApiService
import com.example.cthelper.remote.api.TestManagementApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesNetworkModule {
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTestManagementApiService(retrofit: Retrofit): TestManagementApiService {
        return retrofit.create(TestManagementApiService::class.java)
    }
}
