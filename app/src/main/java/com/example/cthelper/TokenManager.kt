package com.example.cthelper

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "auth_prefs",
        Context.MODE_PRIVATE
    )

    fun saveAccessToken(accessToken: String) {
        prefs.edit()
            .putString("access_token", accessToken)
            .apply()
    }

    fun getAccessToken(): String? = prefs.getString("access_token", null)

    fun clearTokens() {
        prefs.edit()
            .remove("access_token")
            .apply()
    }
}