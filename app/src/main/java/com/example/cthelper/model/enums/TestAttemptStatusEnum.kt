package com.example.cthelper.model.enums

import com.example.cthelper.util.EnumAsIntSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = AttemptStatusSerializer::class)
enum class AttemptStatus(val value: Int) {
    @SerialName("1") IN_PROGRESS(1),
    @SerialName("2") PAUSED(2),
    @SerialName("3") COMPLETED(3),
    @SerialName("4") CANCELLED(4)
}

object AttemptStatusSerializer : EnumAsIntSerializer<AttemptStatus>(
    "AttemptStatus",
    { it.value },
    { v -> AttemptStatus.entries.first { it.value == v } }
)
