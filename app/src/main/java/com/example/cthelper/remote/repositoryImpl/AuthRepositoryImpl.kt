package com.example.cthelper.remote.repositoryImpl

import com.example.cthelper.TokenManager
import com.example.cthelper.domain.repository.AuthRepository
import com.example.cthelper.remote.api.AuthApiService
import com.example.cthelper.remote.request.LoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun login(email: String, password: String): String? {
        val response = authApiService.login(
            LoginRequest(
                email = email,
                password = password,
                clientType = 1,
//                dummy values
                ipAddress = "192.168.0.1",
                deviceInfo = "{}",
                deviceId = "string"
            )
        )
        return if (response.isSuccessful) {
            response.body()?.let {
                print("accessToken: ${it.accessToken}")
                tokenManager.saveAccessToken(it.accessToken)
                it.accessToken
            }
        }
        else {
            print("login failure")
            null
        }
    }
}