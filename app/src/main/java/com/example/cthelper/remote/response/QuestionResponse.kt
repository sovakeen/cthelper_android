package com.example.cthelper.remote.response

import com.example.cthelper.domain.model.Difficulty
import com.example.cthelper.domain.model.QuestionType
import com.example.cthelper.domain.model.QuestionInstance
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    @SerialName("questionId") val questionId: Int,
    @SerialName("code") val code: String,
    @SerialName("type") val type: Int,
    @SerialName("difficulty") val difficulty: Int,
    @SerialName("statement") val statement: String,
    @SerialName("answer") val answer: String,
    @SerialName("explanation") val explanation: String
) {
    fun toQuestion(): QuestionInstance = QuestionInstance(
        userAnswer = "",
        questionInstanceId = questionId,
        code = code,
        type = QuestionType.fromInt(type),
        statement = statement,
        answer = answer,
        explanation = explanation
    )
}
