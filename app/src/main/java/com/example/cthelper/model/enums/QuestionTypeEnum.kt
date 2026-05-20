package com.example.cthelper.model.enums

import com.example.cthelper.model.util.EnumAsIntSerializer
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
