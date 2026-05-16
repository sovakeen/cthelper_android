package com.example.cthelper.domain.repository

import com.example.cthelper.remote.response.LoginResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): String?
}