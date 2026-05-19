package com.example.cthelper.repository

import android.util.Base64
import android.util.Log
import com.example.cthelper.util.TokenManager
import com.example.cthelper.api.AuthApiService
import com.example.cthelper.dto.auth.LoginRequest
import org.json.JSONObject
import java.nio.charset.StandardCharsets
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
                val accessToken: String = it.accessToken

                try {
                    val parts = accessToken.split(".")
                    if (parts.size < 2) {
                        Log.e("ERROR", "Invalid JWT token")
                    }
                    val payloadEncoded = parts[1]
                    val bytes = Base64.decode(payloadEncoded, Base64.URL_SAFE)
                    val jsonString = String(bytes, StandardCharsets.UTF_8)
//                    Log.e("INFO", jsonString)

                    val json = JSONObject(jsonString)
                    val userRole = json.getString("http://schemas.microsoft.com/ws/2008/06/identity/claims/role")
//                    Log.e("INFO", userRole)
                    tokenManager.saveUserRole(userRole)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                print("accessToken: ${accessToken}")
                tokenManager.saveAccessToken(accessToken)
                accessToken
            }
        }
        else {
            print("login failure")
            null
        }
    }
}