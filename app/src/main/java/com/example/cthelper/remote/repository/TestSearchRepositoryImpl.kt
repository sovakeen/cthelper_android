package com.example.cthelper.remote.repository

import com.example.cthelper.domain.model.Test
import com.example.cthelper.domain.repository.TestSearchRepository
import com.example.cthelper.remote.api.TestSearchApiService
import com.example.cthelper.remote.request.TestSearchRequest
import javax.inject.Inject

class TestSearchRepositoryImpl @Inject constructor(
    private val testSearchApiService: TestSearchApiService
): TestSearchRepository {
    override suspend fun getTests(): List<Test>? {
        val response = testSearchApiService.getTests(
            TestSearchRequest()
        )
        return if (response.isSuccessful) {
            response.body()?.let {
                val tests = it.items
                tests.map {
                    it.toTest()
                }
            }
        }
        else {
            null
        }
    }
}
