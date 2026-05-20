package com.example.cthelper

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val AUTH_PREFS_FILE = "auth_prefs"
const val ACCESS_TOKEN_KEY = "access_token"
const val REFRESH_TOKEN_KEY = "refresh_token"
const val USER_ROLE_KEY = "user_role"

@Singleton
class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        AUTH_PREFS_FILE,
        Context.MODE_PRIVATE
    )

    fun saveAccessToken(accessToken: String) {
        prefs.edit()
            .putString(ACCESS_TOKEN_KEY, accessToken)
            .apply()
    }

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN_KEY, null)

    fun saveRefreshToken(refreshToken: String) {
        prefs.edit()
            .putString(REFRESH_TOKEN_KEY, refreshToken)
            .apply()
    }

    fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN_KEY, null)

    fun saveUserRole(userRole: String) {
        prefs.edit()
            .putString(USER_ROLE_KEY, userRole)
            .apply()
    }

    fun getUserRole(): String? = prefs.getString(USER_ROLE_KEY, null)

    fun clearAuthData() {
        prefs.edit()
            .remove(ACCESS_TOKEN_KEY)
            .remove(USER_ROLE_KEY)
            .apply()
    }
}
