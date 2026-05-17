package com.example.cthelper.remote.api

import com.example.cthelper.remote.request.StudentTestsRequest
import com.example.cthelper.remote.response.TestSearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TestManagementApiService {
    @POST("/tests/student/list")
    suspend fun getTests(
        @Body request: StudentTestsRequest
    ): Response<TestSearchResponse>

//    @GET("/tests/{testId}/preview")
//    suspend fun getTest(
//        @Path("testId") testId: Int
//    ): Response<>
}
