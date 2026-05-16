package com.example.cthelper.remote.api

import com.example.cthelper.remote.request.LoginRequest
import com.example.cthelper.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}