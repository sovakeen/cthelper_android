package com.example.cthelper.remote.repository

import com.example.cthelper.domain.model.Test
import com.example.cthelper.domain.repository.TestManagementRepository
import com.example.cthelper.remote.api.TestManagementApiService
import com.example.cthelper.remote.request.StudentTestsRequest
import javax.inject.Inject

class TestManagementRepositoryImpl @Inject constructor(
    private val testManagementApiService: TestManagementApiService
): TestManagementRepository {
    override suspend fun getTests(): List<Test>? {
        val response = testManagementApiService.getTests(
            StudentTestsRequest()
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
