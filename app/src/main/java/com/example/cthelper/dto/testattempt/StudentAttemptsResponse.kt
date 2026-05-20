package com.example.cthelper.dto.testattempt

import com.example.cthelper.model.TestAttempt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentAttemptsResponse(
    @SerialName("items") val attempts: List<TestAttempt>,
    @SerialName("totalPagesCount") val totalPagesCount: Int = 0,
    @SerialName("page") val page: Int = 0,
    @SerialName("pageSize") val pageSize: Int = 0,
    @SerialName("hasPreviousPage") val hasPreviousPage: Boolean = true,
    @SerialName("hasNextPage") val hasNextPage: Boolean = true
)
