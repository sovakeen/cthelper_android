package com.example.cthelper.domain.repository

import com.example.cthelper.domain.model.Test

interface TestManagementRepository {
    suspend fun getTests(): List<Test>?
}
