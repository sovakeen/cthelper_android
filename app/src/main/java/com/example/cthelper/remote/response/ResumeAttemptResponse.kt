package com.example.cthelper.remote.response

import com.example.cthelper.remote.serialization.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = AttemptStatusSerializer::class)
enum class AttemptStatus(val value: Int) {
    @SerialName("1") TYPE1(1),
    @SerialName("2") TYPE2(2)
}

object AttemptStatusSerializer : EnumAsIntSerializer<AttemptStatus>(
    "AttemptStatus",
    { it.value },
    { v -> AttemptStatus.entries.first { it.value == v } }
)

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
