package com.example.cthelper.model

import com.example.cthelper.model.enums.QuestionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionInstance(
    @SerialName("userAnswerId") val questionInstanceId: Int,
    @SerialName("userAnswer") val userAnswer: String = "",
    @SerialName("code") val code: String,
    @SerialName("type") val type: QuestionType,
    @SerialName("statement") val statement: String,
    @SerialName("answer") val answer: String? = null,
    @SerialName("explanation") val explanation: String? = null
) {
    fun toUserAnswer(): UserAnswer = UserAnswer(
        questionInstanceId = questionInstanceId,
        userAnswer = userAnswer
    )
}
