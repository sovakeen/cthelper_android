package com.example.cthelper.domain.model

enum class QuestionType(val value: Int) {
    SINGLE_CHOICE(1),
    MULTIPLE_CHOICE(2),
    OPEN_ENDED(3);

    companion object {
        private val map = entries.associateBy(QuestionType::value)
        fun fromInt(type: Int): QuestionType = map[type]!!
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

data class Question(
    val questionId: Int,
    val code: String,
    val type: QuestionType,
    val difficulty: Difficulty,
    val statement: String,
    val answer: String,
    val explanation: String
)
