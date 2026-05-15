package com.example.cthelper.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String
)