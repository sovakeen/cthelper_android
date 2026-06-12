package com.example.cthelper.feature.student.attemptreview

import com.example.cthelper.network.api.AttemptDetailsResponse
import com.example.cthelper.network.api.TestAttemptApiService
import retrofit2.HttpException
import javax.inject.Inject

interface AttemptReviewRepository {
    suspend fun getAttemptDetails(attemptId: Int): AttemptDetailsResponse
}

class AttemptReviewRepositoryImpl @Inject constructor(
    private val testAttemptApiService: TestAttemptApiService
): AttemptReviewRepository {
    override suspend fun getAttemptDetails(attemptId: Int): AttemptDetailsResponse {
        val response = testAttemptApiService.attemptDetails(attemptId)
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            print(body)
            return body
        } else { throw HttpException(response) }
    }
}
