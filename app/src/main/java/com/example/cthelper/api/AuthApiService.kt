package com.example.cthelper.api

import com.example.cthelper.dto.auth.LoginRequest
import com.example.cthelper.dto.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
