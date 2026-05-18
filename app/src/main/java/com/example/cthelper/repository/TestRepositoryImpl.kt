package com.example.cthelper.repository

import com.example.cthelper.api.TestApiService
import com.example.cthelper.model.Test
import com.example.cthelper.dto.test.StudentTestsRequest
import retrofit2.HttpException
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testApiService: TestApiService
): TestRepository {
    override suspend fun getTests(): List<Test> {
        val response = testApiService.getTests(
            StudentTestsRequest()
        )
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body.tests
        } else {
            throw HttpException(response)
        }
    }
}
