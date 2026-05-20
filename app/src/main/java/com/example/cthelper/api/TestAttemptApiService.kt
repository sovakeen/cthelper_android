package com.example.cthelper.api

import com.example.cthelper.dto.testattempt.CompleteAttemptResponse
import com.example.cthelper.dto.testattempt.ResumeAttemptResponse
import com.example.cthelper.dto.testattempt.StartAttemptResponse
import com.example.cthelper.dto.testattempt.StudentAttemptsResponse
import com.example.cthelper.model.TestAttempt
import com.example.cthelper.model.UserAnswer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TestAttemptApiService {
    @GET("/attempts/me/list")
    suspend fun getAttempts(
        @Query("searchTerm") searchTerm: String = "",
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Response<StudentAttemptsResponse>

    @GET("/attempts/me/{attemptId}")
    suspend fun getAttemptDetails(
        @Path("attemptId") attemptId: Int
    ): Response<TestAttempt>

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
