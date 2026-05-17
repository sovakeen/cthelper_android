package com.example.cthelper.remote.response

import com.example.cthelper.domain.model.Difficulty
import com.example.cthelper.domain.model.Test
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestSearchResponse(
    @SerialName("items") val tests: List<TestItem>,
    @SerialName("totalPagesCount") val totalPagesCount: Int,
    @SerialName("page") val page: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("hasPreviousPage") val hasPreviousPage: Boolean,
    @SerialName("hasNextPage") val hasNextPage: Boolean
)

@Serializable
data class TestItem(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("authorName") val authorName: String,
    @SerialName("problemCount") val questionCount: Int,
    @SerialName("avgDifficult") val avgDifficult: Int
){
    fun toTest(): Test = Test(
        testId = testId,
        testName = testName,
        authorName = authorName,
        questionCount = questionCount,
        avgDifficulty = Difficulty.fromInt(avgDifficult)
    )
}
