package com.example.cthelper.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestAttempt (
    @SerialName("testAttemptId") val testAttemptId: Int
)
