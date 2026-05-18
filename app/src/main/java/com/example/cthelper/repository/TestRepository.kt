package com.example.cthelper.repository

import com.example.cthelper.model.Test

interface TestRepository {
    suspend fun getTests(): List<Test>
}
