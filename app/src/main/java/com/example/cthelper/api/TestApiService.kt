package com.example.cthelper.api

import com.example.cthelper.dto.test.StudentTestsRequest
import com.example.cthelper.dto.test.StudentTestsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TestApiService {
    @POST("/tests/student/list")
    suspend fun getTests(
        @Body request: StudentTestsRequest
    ): Response<StudentTestsResponse>
}
