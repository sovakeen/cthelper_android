package com.example.cthelper.interceptor

import com.example.cthelper.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CookieInterceptor @Inject constructor(
    val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cookies = response.headers("Set-Cookie")
        for (cookie in cookies) {
            if (cookie.startsWith("refresh_token=")) {
                val refreshToken = cookie.split(";")[0].substringAfter("=")
//                Log.e("INFO", "Refresh token: ${refreshToken}")
                tokenManager.saveRefreshToken(refreshToken)
            }
        }

        return response
    }
}
