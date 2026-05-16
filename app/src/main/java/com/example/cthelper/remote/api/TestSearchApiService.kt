package com.example.cthelper.remote.api

import com.example.cthelper.remote.request.TestSearchRequest
import com.example.cthelper.remote.response.TestSearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TestSearchApiService {
    @POST("/tests/student/list")
    suspend fun getTests(
        @Body request: TestSearchRequest
    ): Response<TestSearchResponse>
}