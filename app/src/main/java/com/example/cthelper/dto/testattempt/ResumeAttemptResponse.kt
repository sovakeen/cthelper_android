package com.example.cthelper.dto.testattempt

import com.example.cthelper.model.QuestionInstance
import com.example.cthelper.model.enums.AttemptStatus
import com.example.cthelper.model.enums.QuestionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeAttemptResponse(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("attemptId") val attemptId: Int,
    @SerialName("status") val status: AttemptStatus,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int,
    @SerialName("questionsState") val questionInstances: List<QuestionInstance>
)
