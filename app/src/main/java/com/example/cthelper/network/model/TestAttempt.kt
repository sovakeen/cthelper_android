package com.example.cthelper.network.model

import com.example.cthelper.network.model.enums.AttemptStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestAttempt (
    @SerialName("testAttemptId") val testAttemptId: Int,
    @SerialName("testName") val testName: String,
//    @SerialName("testId") val testId: Int = 0,
    @SerialName("studentId") val studentId: Int = 0,
    @SerialName("studentName") val studentName: String= "",
    @SerialName("status") val status: AttemptStatus,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int = 0,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("userAnswers") val userAnswers: List<QuestionInstanceExt> = listOf()
)
