package com.example.cthelper.repository

import com.example.cthelper.model.UserAnswer
import com.example.cthelper.api.TestAttemptApiService
import com.example.cthelper.dto.testattempt.CompleteAttemptRequest
import com.example.cthelper.dto.testattempt.CompleteAttemptResponse
import com.example.cthelper.dto.testattempt.PauseAttemptRequest
import com.example.cthelper.dto.testattempt.ResumeAttemptResponse
import com.example.cthelper.dto.testattempt.StartAttemptResponse
import retrofit2.HttpException
import javax.inject.Inject

class TestAttemptRepositoryImpl @Inject constructor(
    private val testAttemptApiService: TestAttemptApiService
): TestAttemptRepository {
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
            PauseAttemptRequest(userAnswers)
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
            CompleteAttemptRequest(userAnswers)
        )
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body
        }
        else { throw HttpException(response)
        }
    }

    override suspend fun cancelAttempt(attemptId: Int) {
        val response = testAttemptApiService.cancelAttempt(
            attemptId
        )
        if (!response.isSuccessful)  { throw HttpException(response)
        }
    }
}
