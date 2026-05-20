package com.example.cthelper.model

import com.example.cthelper.model.enums.QuestionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionInstanceExt(
    @SerialName("problemId") val questionInstanceId: Int,
    @SerialName("isActualProblemVersion") val isActualQuestionInstance: Boolean,
    @SerialName("statement") val statement: String,
    @SerialName("answer") val userAnswer: String = "",
    @SerialName("isCorrect") val isCorrect: Boolean,
    @SerialName("correctAnswer") val answer: String? = null,
    @SerialName("explanation") val explanation: String? = null,
    @SerialName("type") val type: QuestionType,
    @SerialName("difficulty") val difficulty: Double? = 0.0,
    @SerialName("topicName") val topicName: String? = "",
)
