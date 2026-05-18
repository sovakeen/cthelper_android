package com.example.cthelper.dto.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentTestsRequest(
    @SerialName("page") val page: Int = 0,
    @SerialName("pageSize") val pageSize: Int = 0,
    @SerialName("nameFragment") val nameFragment: String? = null,
    @SerialName("authorNameFragment") val authorNameFragment: String? = null,
    @SerialName("avgDifficult") val avgDifficult: Double? = null,
    @SerialName("isTraning") val isTraining: Boolean = false,
    @SerialName("type") val type: Int? = null,
    @SerialName("maxTaskCount") val maxTaskCount: Int? = null,
    @SerialName("minTaskCount") val minTaskCount: Int? = null,
    @SerialName("assignedToMe") val assignedToMe: Boolean = false
)
