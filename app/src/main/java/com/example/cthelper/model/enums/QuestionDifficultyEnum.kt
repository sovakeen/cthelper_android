package com.example.cthelper.model.enums

import com.example.cthelper.util.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = DifficultySerializer::class)
enum class QuestionDifficulty(val value: Int) {
    @SerialName("1") VERY_EASY(1),
    @SerialName("2") EASY(2),
    @SerialName("3") NORMAL(3),
    @SerialName("4") HARD(4),
    @SerialName("5") VERY_HARD(5);

    companion object {
        private val map = entries.associateBy(QuestionDifficulty::value)
        fun fromInt(type: Int): QuestionDifficulty = map[type]!!
    }
}

object DifficultySerializer : EnumAsIntSerializer<QuestionDifficulty>(
    "Difficulty",
    { it.value },
    { v -> QuestionDifficulty.fromInt(v) }
)
