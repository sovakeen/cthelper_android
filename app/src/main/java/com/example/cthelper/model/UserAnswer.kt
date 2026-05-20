package com.example.cthelper.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnswer(
    @SerialName("userAnswerId") val questionInstanceId: Int,
    @SerialName("userAnswer") val userAnswer: String? = null,
)
