package com.example.cthelper.dto.testattempt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompleteAttemptResponse(
    @SerialName("attemptId") val attemptId: Int
)
