package com.example.cthelper.dto.testattempt

import com.example.cthelper.model.UserAnswer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PauseAttemptRequest(
    @SerialName("answers") val answers: List<UserAnswer>
)
