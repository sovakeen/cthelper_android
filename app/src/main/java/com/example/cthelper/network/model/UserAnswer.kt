package com.example.cthelper.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnswer(
    @SerialName("userAnswerId") val questionInstanceId: Int,
    @SerialName("answer") val userAnswer: String? = null,
)
