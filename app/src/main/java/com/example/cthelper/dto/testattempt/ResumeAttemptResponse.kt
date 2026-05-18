package com.example.cthelper.dto.testattempt

import com.example.cthelper.model.enums.AttemptStatus
import com.example.cthelper.util.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionState(
    @SerialName("userAnswer") val userAnswer: String,
    @SerialName("userAnswerId") val userAnswerId: Int,
    @SerialName("code") val code: String,
    @SerialName("type") val type: Int,
    @SerialName("statement") val statement: String
)

@Serializable
data class ResumeAttemptResponse(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("attemptId") val attemptId: Int,
    @SerialName("status") val status: AttemptStatus,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int,
    @SerialName("questionsState") val questionsState: List<QuestionState>
)
