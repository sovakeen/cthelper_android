package com.example.cthelper.data.local.models

data class Test(
    val testId: Int,
    val testName: String,
    val authorName: String,
    val problemCount: Int,
    val avgDifficulty: Difficulty
)
