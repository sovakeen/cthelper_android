package com.example.cthelper.di

import com.example.cthelper.api.AuthApiService
import com.example.cthelper.api.TestApiService
import com.example.cthelper.api.TestAttemptApiService
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
    fun provideTestAttemptApiService(retrofit: Retrofit): TestAttemptApiService {
        return retrofit.create(TestAttemptApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTestApiService(retrofit: Retrofit): TestApiService {
        return retrofit.create(TestApiService::class.java)
    }
}
