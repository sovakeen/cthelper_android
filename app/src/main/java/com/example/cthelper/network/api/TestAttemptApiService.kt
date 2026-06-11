package com.example.cthelper.network.api

import com.example.cthelper.network.model.QuestionInstance
import com.example.cthelper.network.model.QuestionInstanceExt
import com.example.cthelper.network.model.TestAttempt
import com.example.cthelper.network.model.UserAnswer
import com.example.cthelper.network.model.enums.AttemptStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TestAttemptApiService {
    @GET("/attempts/me/list")
    suspend fun attemptsList(
        @Query("searchTerm") searchTerm: String = "",
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Response<AttemptsListResponse>

    @GET("/attempts/me/{attemptId}")
    suspend fun attemptDetails(
        @Path("attemptId") attemptId: Int
    ): Response<AttemptDetailsResponse>

    @POST("/attempts/start/{testId}")
    suspend fun startAttempt(
        @Path("testId") testId: Int
    ): Response<StartAttemptResponse>

    @PATCH("/attempts/{attemptId}/pause")
    suspend fun pauseAttempt(
        @Path("attemptId") attemptId: Int,
        @Body request: List<UserAnswer>,
    ): Response<Unit>

    @PATCH("/attempts/{attemptId}/resume")
    suspend fun resumeAttempt(
        @Path("attemptId") attemptId: Int
    ): Response<ResumeAttemptResponse>

    @PATCH("/attempts/{attemptId}/complete")
    suspend fun completeAttempt(
        @Path("attemptId") attemptId: Int,
        @Body request: List<UserAnswer>,
    ): Response<CompleteAttemptResponse>

    @PATCH("/attempts/{attemptId}/cancel")
    suspend fun cancelAttempt(
        @Path("attemptId") attemptId: Int
    ): Response<Unit>
}

// attemptsList ---------------------------------------------------------------------

@Serializable
data class AttemptsListResponse(
    @SerialName("items") val attempts: List<TestAttempt>,
    @SerialName("totalPagesCount") val totalPagesCount: Int = 0,
    @SerialName("page") val page: Int = 0,
    @SerialName("pageSize") val pageSize: Int = 0,
    @SerialName("hasPreviousPage") val hasPreviousPage: Boolean = true,
    @SerialName("hasNextPage") val hasNextPage: Boolean = true
)

// attemptDetails ---------------------------------------------------------------------

@Serializable
data class AttemptDetailsResponse (
    @SerialName("testAttemptId") val testAttemptId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("testId") val testId: Int = 0,
    @SerialName("studentId") val studentId: Int = 0,
    @SerialName("studentName") val studentName: String= "",
    @SerialName("status") val status: AttemptStatus,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int = 0,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("userAnswers") val userAnswers: List<QuestionInstanceExt> = listOf()
)

// startAttempt ---------------------------------------------------------------------

@Serializable
data class StartAttemptResponse(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("attemptId") val attemptId: Int,
    @SerialName("status") val status: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int?,
    @SerialName("problems") val questionInstances: List<QuestionInstance>
)

// resumeAttempt ---------------------------------------------------------------------

@Serializable
data class ResumeAttemptResponse(
    @SerialName("testId") val testId: Int,
    @SerialName("testName") val testName: String,
    @SerialName("attemptId") val attemptId: Int,
    @SerialName("status") val status: AttemptStatus,
    @SerialName("duration") val duration: Int,
    @SerialName("rawScore") val rawScore: Int,
    @SerialName("questionsState") val questionInstances: List<QuestionInstance>
)

// completeAttempt ---------------------------------------------------------------------

@Serializable
data class CompleteAttemptResponse(
    @SerialName("attemptId") val attemptId: Int
)
