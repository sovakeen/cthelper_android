package com.example.cthelper.feature.student.testslist

import com.example.cthelper.network.api.TestApiService
import com.example.cthelper.network.api.TestsListRequest
import com.example.cthelper.network.model.Test
import retrofit2.HttpException
import javax.inject.Inject

interface TestsListRepository {
    suspend fun getTests(): List<Test>
}

class TestsListRepositoryImpl @Inject constructor(
    private val testApiService: TestApiService
): TestsListRepository {
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
