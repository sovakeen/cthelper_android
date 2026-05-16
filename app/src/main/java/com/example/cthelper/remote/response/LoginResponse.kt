package com.example.cthelper.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String
)