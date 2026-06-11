package com.example.cthelper.repository

import com.example.cthelper.network.api.AttemptDetailsResponse
import com.example.cthelper.network.api.AttemptsListResponse
import com.example.cthelper.network.api.CompleteAttemptResponse
import com.example.cthelper.network.api.ResumeAttemptResponse
import com.example.cthelper.network.api.StartAttemptResponse
import com.example.cthelper.network.api.TestAttemptApiService
import com.example.cthelper.network.model.UserAnswer
import retrofit2.HttpException
import javax.inject.Inject

interface TestAttemptRepository {
    suspend fun getAttempts(): AttemptsListResponse
    suspend fun getAttemptDetails(attemptId: Int): AttemptDetailsResponse
    suspend fun startAttempt(testId: Int): StartAttemptResponse
    suspend fun pauseAttempt(attemptId: Int, userAnswers: List<UserAnswer>): Unit
    suspend fun resumeAttempt(attemptId: Int): ResumeAttemptResponse
    suspend fun completeAttempt(attemptId: Int, userAnswers: List<UserAnswer>): CompleteAttemptResponse
    suspend fun cancelAttempt(attemptId: Int): Unit
}

class TestAttemptRepositoryImpl @Inject constructor(
    private val testAttemptApiService: TestAttemptApiService
): TestAttemptRepository {
    override suspend fun getAttempts(): AttemptsListResponse {
        val response = testAttemptApiService.attemptsList()
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            print(body)
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun getAttemptDetails(attemptId: Int): AttemptDetailsResponse {
        val response = testAttemptApiService.attemptDetails(attemptId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            print(body)
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun startAttempt(testId: Int): StartAttemptResponse {
        val response = testAttemptApiService.startAttempt(testId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            print(body)
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun pauseAttempt(attemptId: Int, userAnswers: List<UserAnswer>): Unit {
        val response = testAttemptApiService.pauseAttempt(
            attemptId,
            userAnswers
        )
        if (!response.isSuccessful) { throw HttpException(response)
        }
    }

    override suspend fun resumeAttempt(attemptId: Int): ResumeAttemptResponse {
        val response = testAttemptApiService.resumeAttempt(attemptId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun completeAttempt(attemptId: Int, userAnswers: List<UserAnswer>): CompleteAttemptResponse {
        val response = testAttemptApiService.completeAttempt(
            attemptId,
            userAnswers
        )
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
//            Log.e("INFO", "successful attempt ${body.attemptId} complete")
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun cancelAttempt(attemptId: Int) {
        val response = testAttemptApiService.cancelAttempt(
            attemptId
        )
        if (!response.isSuccessful)  { throw HttpException(response) }
    }
}
