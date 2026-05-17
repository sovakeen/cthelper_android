package com.example.cthelper.remote.api

import com.example.cthelper.remote.request.StudentTestsRequest
import com.example.cthelper.remote.request.UserAnswersRequest
import com.example.cthelper.remote.response.CompleteAttemptResponse
import com.example.cthelper.remote.response.ResumeAttemptResponse
import com.example.cthelper.remote.response.StartAttemptResponse
import com.example.cthelper.remote.response.TestSearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TestManagementApiService {
    @POST("/tests/student/list")
    suspend fun getTests(
        @Body request: StudentTestsRequest
    ): Response<TestSearchResponse>

    @POST("/attempts/start/{testId}")
    suspend fun startAttempt(
        @Path("testId") testId: Int
    ): Response<StartAttemptResponse>

    @PATCH("/attempts/{attemptId}/pause")
    suspend fun pauseAttempt(
        @Path("attemptId") attemptId: Int,
        @Body request: UserAnswersRequest,
    ): Response<Unit>

    @PATCH("/attempts/{attemptId}/resume")
    suspend fun resumeAttempt(
        @Path("attemptId") attemptId: Int
    ): Response<ResumeAttemptResponse>

    @PATCH("/attempts/{attemptId}/complete")
    suspend fun completeAttempt(
        @Path("attemptId") attemptId: Int,
        @Body request: UserAnswersRequest,
    ): Response<CompleteAttemptResponse>

    @PATCH("UserAnswersRequest")
    suspend fun cancelAttempt(
        @Path("attemptId") attemptId: Int
    ): Response<Unit>
}
