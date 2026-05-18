package com.example.cthelper.dto.test

import com.example.cthelper.model.Test
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentTestsResponse(
    @SerialName("items") val tests: List<Test>,
    @SerialName("totalPagesCount") val totalPagesCount: Int,
    @SerialName("page") val page: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("hasPreviousPage") val hasPreviousPage: Boolean,
    @SerialName("hasNextPage") val hasNextPage: Boolean
)
