package com.example.cthelper.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("clientType") val clientType: Int,
    @SerialName("ipAddress") val ipAddress: String,
    @SerialName("deviceInfo") val deviceInfo: String,
    @SerialName("deviceId") val deviceId: String
)
