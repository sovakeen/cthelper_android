package com.example.cthelper.remote.repositoryImpl

import com.example.cthelper.domain.model.Test
import com.example.cthelper.domain.model.UserAnswer
import com.example.cthelper.domain.repository.TestManagementRepository
import com.example.cthelper.remote.api.TestManagementApiService
import com.example.cthelper.remote.request.StudentTestsRequest
import com.example.cthelper.remote.request.UserAnswersRequest
import com.example.cthelper.remote.response.CompleteAttemptResponse
import com.example.cthelper.remote.response.ResumeAttemptResponse
import com.example.cthelper.remote.response.StartAttemptResponse
import retrofit2.HttpException
import javax.inject.Inject

class TestManagementRepositoryImpl @Inject constructor(
    private val testManagementApiService: TestManagementApiService
): TestManagementRepository {
    override suspend fun getTests(): List<Test> {
        val response = testManagementApiService.getTests(
            StudentTestsRequest()
        )
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body.tests.map { it.toTest() }
        }
        else { throw HttpException(response) }
    }

    override suspend fun startAttempt(testId: Int): StartAttemptResponse {
        val response = testManagementApiService.startAttempt(testId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            print(body)
            return body
        }
        else { throw HttpException(response) }
    }

    override suspend fun pauseAttempt(attemptId: Int, userAnswers: List<UserAnswer>): Unit {
        val response = testManagementApiService.pauseAttempt(
            attemptId,
            UserAnswersRequest(userAnswers)
        )
        if (!response.isSuccessful) { throw HttpException(response) }
    }

    override suspend fun resumeAttempt(attemptId: Int): ResumeAttemptResponse {
        val response = testManagementApiService.resumeAttempt(attemptId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body
        }
        else { throw HttpException(response) }
    }

    override suspend fun completeAttempt(attemptId: Int, userAnswers: List<UserAnswer>): CompleteAttemptResponse {
        val response = testManagementApiService.completeAttempt(
            attemptId,
            UserAnswersRequest(userAnswers)
        )
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body
        }
        else { throw HttpException(response) }
    }

    override suspend fun cancelAttempt(attemptId: Int) {
        val response = testManagementApiService.getTests(
            StudentTestsRequest()
        )
        if (!response.isSuccessful)  { throw HttpException(response) }
    }
}
