package com.example.cthelper.remote.request

import com.example.cthelper.domain.model.UserAnswer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAnswersRequest(
    @SerialName("answers") val answers: List<UserAnswer>
)
