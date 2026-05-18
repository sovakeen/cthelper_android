package com.example.cthelper.model

import com.example.cthelper.model.enums.QuestionType
import com.example.cthelper.util.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionInstance(
    @SerialName("userAnswerId") val questionInstanceId: Int,
    @SerialName("userAnswer") val userAnswer: String? = null,
    @SerialName("code") val code: String,
    @SerialName("type") val type: QuestionType,
    @SerialName("statement") val statement: String,
    @SerialName("answer") val answer: String? = null,
    @SerialName("explanation") val explanation: String? = null
)
