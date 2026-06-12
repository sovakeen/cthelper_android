package com.example.cthelper.network.api

import com.example.cthelper.network.model.Test
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TestApiService {
    @POST("/tests/student/list")
    suspend fun testsList(
        @Body request: TestsListRequest
    ): Response<TestsListResponse>

    @GET("/tests/{testId}/details")
    suspend fun testDetails(
        @Path("testId") testId: Int
    ): Response<Test>
}

// testsList ---------------------------------------------------------------------

@Serializable
data class TestsListRequest(
    @SerialName("page") val page: Int = 0,
    @SerialName("pageSize") val pageSize: Int = 0,
    @SerialName("nameFragment") val nameFragment: String? = null,
    @SerialName("authorNameFragment") val authorNameFragment: String? = null,
    @SerialName("avgDifficult") val avgDifficult: Double? = null,
    @SerialName("isTraning") val isTraining: Boolean = false,
    @SerialName("type") val type: Int? = null,
    @SerialName("maxTaskCount") val maxTaskCount: Int? = null,
    @SerialName("minTaskCount") val minTaskCount: Int? = null,
    @SerialName("assignedToMe") val assignedToMe: Boolean = false
)

@Serializable
data class TestsListResponse(
    @SerialName("items") val tests: List<Test>,
    @SerialName("totalPagesCount") val totalPagesCount: Int,
    @SerialName("page") val page: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("hasPreviousPage") val hasPreviousPage: Boolean,
    @SerialName("hasNextPage") val hasNextPage: Boolean
)
