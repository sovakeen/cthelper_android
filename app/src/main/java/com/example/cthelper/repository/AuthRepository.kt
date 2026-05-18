package com.example.cthelper.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): String?
}
