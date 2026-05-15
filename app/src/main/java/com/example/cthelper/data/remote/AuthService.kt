package com.example.cthelper.data.remote

import com.example.cthelper.data.remote.request.LoginRequest
import com.example.cthelper.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
