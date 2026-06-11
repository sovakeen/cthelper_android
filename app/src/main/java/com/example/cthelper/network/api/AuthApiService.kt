package com.example.cthelper.network.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}

// login ---------------------------------------------------------------------

@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("clientType") val clientType: Int,
    @SerialName("ipAddress") val ipAddress: String,
    @SerialName("deviceInfo") val deviceInfo: String,
    @SerialName("deviceId") val deviceId: String
)

@Serializable
data class LoginResponse(
    @SerialName("accessToken") val accessToken: String
)
