package com.example.cthelper.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnswer(
    @SerialName("userAnswer") val userAnswer: String,
    @SerialName("userAnswerId") val userAnswerId: Int
)
