package com.example.cthelper.network.api

import com.example.cthelper.network.model.Test
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface StatisticsApiService {
    @GET("/statistics/me")
    suspend fun getStats(
        @Query("subjectId") subjectId: Int = 1
    ): Response<StatisticsResponse>
}

@Serializable
data class StatisticsResponse(
    @SerialName("fromDate") val fromDate: String? = null,
    @SerialName("toDate") val toDate: String? = null,
    @SerialName("commonRate") val commonRate: Int = 0,
    @SerialName("medianRate") val medianRate: Int = 0,
    @SerialName("totalAnswers") val totalAnswers: Int = 0,
    @SerialName("correctAnswers") val correctAnswers: Int = 0,
    @SerialName("totalAttempts") val totalAttempts: Int = 0,
    @SerialName("statisticsByTopicList") val statisticsByTopicList: List<TopicStatistics> = emptyList(),
    @SerialName("pendingTopicList") val pendingTopicList: List<PendingTopic> = emptyList(),
    @SerialName("topicToReviewList") val topicToReviewList: List<TopicToReview> = emptyList()
)

@Serializable
data class TopicStatistics(
    @SerialName("topicId") val topicId: Int,
    @SerialName("topicName") val topicName: String,
    @SerialName("averageSuccessRate") val averageSuccessRate: Int,
    @SerialName("medianSuccessRate") val medianSuccessRate: Int,
    @SerialName("successRateByDifficultList") val successRateByDifficultList: List<DifficultyStat>? = null
)

@Serializable
data class DifficultyStat(
    @SerialName("difficult") val difficult: Int,
    @SerialName("successRate") val successRate: Int,
    @SerialName("medianSuccessRate") val medianSuccessRate: Int
)

@Serializable
data class PendingTopic(
    @SerialName("topicId") val topicId: Int,
    @SerialName("topicName") val topicName: String
)

@Serializable
data class TopicToReview(
    @SerialName("topicId") val topicId: Int,
    @SerialName("topicName") val topicName: String
    // при необходимости добавьте другие поля
)
