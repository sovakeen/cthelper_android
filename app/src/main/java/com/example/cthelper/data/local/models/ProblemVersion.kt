package com.example.cthelper.data.local.models

enum class ProblemType(val value: Int) {
    SINGLE_CHOICE(1),
    MULTIPLE_CHOICE(2),
    OPEN_ENDED(3);

    companion object {
        private val map = entries.associateBy(ProblemType::value)
        fun fromInt(type: Int): ProblemType = map[type]!!
    }
}

enum class Difficulty(val value: Int) {
    VERY_EASY(1),
    EASY(2),
    NORMAL(3),
    HARD(4),
    VERY_HARD(5);

    companion object {
        private val map = entries.associateBy(Difficulty::value)
        fun fromInt(type: Int): Difficulty = map[type]!!
    }
}

data class ProblemVersion(
    val problemId: Int,
    val code: String,
    val type: ProblemType,
    val difficulty: Difficulty,
    val statement: String,
    val answer: String,
    val explanation: String
)
