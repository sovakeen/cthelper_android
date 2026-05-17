package com.example.cthelper.domain.model

data class Test(
    val testId: Int,
    val testName: String,
    val authorName: String,
    val questionCount: Int,
    val avgDifficulty: Difficulty
)
