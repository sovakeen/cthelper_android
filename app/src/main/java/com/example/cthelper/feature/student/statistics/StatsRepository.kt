package com.example.cthelper.feature.student.statistics

import com.example.cthelper.network.api.StatisticsApiService
import com.example.cthelper.network.api.StatisticsResponse
import retrofit2.HttpException
import javax.inject.Inject

interface StatsRepository {
    suspend fun getStats(): StatisticsResponse
}

class StatsRepositoryImpl @Inject constructor(
    private val statisticsApiService: StatisticsApiService
): StatsRepository {
    override suspend fun getStats(): StatisticsResponse {
        val response = statisticsApiService.getStats()
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Successful response had no body")
            return body
        } else {
            throw HttpException(response)
        }
    }
}
