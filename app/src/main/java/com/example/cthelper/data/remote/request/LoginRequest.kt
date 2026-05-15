package com.example.cthelper.data.remote.request

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