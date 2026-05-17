package com.example.cthelper.domain.model

import com.example.cthelper.remote.serialization.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = QuestionTypeSerializer::class)
enum class QuestionType(val value: Int) {
    @SerialName("1") SINGLE_CHOICE(1),
    @SerialName("2") MULTIPLE_CHOICE(2),
    @SerialName("3") OPEN_ENDED(3);

    companion object {
        private val map = entries.associateBy(QuestionType::value)
        fun fromInt(type: Int): QuestionType = map[type]!!
    }
}

object QuestionTypeSerializer : EnumAsIntSerializer<QuestionType>(
    "QuestionType",
    { it.value },
    { v -> QuestionType.fromInt(v) }
)

@Serializable(with = DifficultySerializer::class)
enum class Difficulty(val value: Int) {
    @SerialName("1") VERY_EASY(1),
    @SerialName("2") EASY(2),
    @SerialName("3") NORMAL(3),
    @SerialName("4") HARD(4),
    @SerialName("5") VERY_HARD(5);

    companion object {
        private val map = entries.associateBy(Difficulty::value)
        fun fromInt(type: Int): Difficulty = map[type]!!
    }
}

object DifficultySerializer : EnumAsIntSerializer<Difficulty>(
    "Difficulty",
    { it.value },
    { v -> Difficulty.fromInt(v) }
)

@Serializable
data class QuestionInstance(
    @SerialName("userAnswerId") val questionInstanceId: Int,
    @SerialName("userAnswer") val userAnswer: String?,
    @SerialName("code") val code: String,
    @SerialName("type") val type: QuestionType,
    @SerialName("statement") val statement: String,
    @SerialName("answer") val answer: String? = null,
    @SerialName("explanation") val explanation: String? = null
)
