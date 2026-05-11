package com.example.cthelper.data.remote

import com.example.cthelper.data.remote.models.LoginRequest
import com.example.cthelper.data.remote.models.LoginResponse
import com.example.cthelper.data.remote.models.RefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("auth/refresh-token")
    suspend fun refreshToken(
        @Field("token") token: String
    ): Response<RefreshResponse>
}
