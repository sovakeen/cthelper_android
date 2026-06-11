package com.example.cthelper.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("authorName") val authorName: String,
    @SerialName("problemCount") val questionCount: Int,
    @SerialName("avgDifficult") val avgDifficulty: Double? = null
)
