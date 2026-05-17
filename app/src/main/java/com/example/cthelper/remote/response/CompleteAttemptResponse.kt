package com.example.cthelper.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompleteAttemptResponse(
    @SerialName("attemptId") val attemptId: Int
)
