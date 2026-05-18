package com.example.cthelper.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnswer(
    @SerialName("userAnswer") val userAnswer: String? = null,
    @SerialName("userAnswerId") val userAnswerId: Int
)
