package com.example.cthelper.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    val clientType: Int,
    val ipAddress: String,
    val deviceInfo: String,
    val deviceId: String
)

@Serializable
data class LoginResponse(
    val accessToken: String
)


@Serializable
data class RefreshResponse(
    val accessToken: String
)
