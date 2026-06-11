package com.example.cthelper.repository

import com.example.cthelper.network.api.TestApiService
import com.example.cthelper.network.api.TestsListRequest
import com.example.cthelper.network.model.Test
import retrofit2.HttpException
import javax.inject.Inject

interface TestRepository {
    suspend fun getTests(): List<Test>
}

class TestRepositoryImpl @Inject constructor(
    private val testApiService: TestApiService
): TestRepository {
    override suspend fun getTests(): List<Test> {
        val response = testApiService.testsList(
            TestsListRequest()
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
