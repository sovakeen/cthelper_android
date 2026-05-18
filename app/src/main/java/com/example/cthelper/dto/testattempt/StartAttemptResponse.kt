package com.example.cthelper.dto.testattempt

import com.example.cthelper.model.QuestionInstance
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StartAttemptResponse(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("attemptId") val attemptId: Int,
    @SerialName("status") val status: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int?,
    @SerialName("problems") val questionInstances: List<QuestionInstance>
)
