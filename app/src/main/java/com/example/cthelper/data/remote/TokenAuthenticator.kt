package com.example.cthelper.data.remote

import com.example.cthelper.data.local.TokenManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authServiceProvider: Provider<AuthService>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Use the current access token as the token to refresh
        val currentToken = tokenManager.getAccessToken() ?: return null

        synchronized(this) {
            val latestToken = tokenManager.getAccessToken()

            // If the token was already refreshed by another thread, use the new one
            if (response.request.header("Authorization") != "Bearer $latestToken") {
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $latestToken")
                    .build()
            }

            // Refresh token call
            val authService = authServiceProvider.get()
            val refreshResponse = try {
                // Using runBlocking because authenticate is called from a background thread
                kotlinx.coroutines.runBlocking {
                    authService.refreshToken(currentToken)
                }
            } catch (e: Exception) {
                null
            }

            return if (refreshResponse?.isSuccessful == true && refreshResponse.body() != null) {
                val newToken = refreshResponse.body()!!.accessToken
                tokenManager.saveAccessToken(newToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
            } else {
                // If refresh fails (e.g., token expired or invalid), clear tokens
                tokenManager.clearTokens()
                null
            }
        }
    }
}
