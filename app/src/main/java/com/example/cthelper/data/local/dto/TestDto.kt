package com.example.cthelper.data.local.dto

import com.example.cthelper.data.local.models.Difficulty
import com.example.cthelper.data.local.models.Test

data class TestDto(
    val testId: Int,
    val testName: String,
    val authorName: String,
    val problemCount: Int,
    val avgDifficulty: Int
) {
    fun toTest(): Test = Test(
        testId = testId,
        testName = testName,
        authorName = authorName,
        problemCount = problemCount,
        avgDifficulty = Difficulty.fromInt(avgDifficulty)
    )
}
