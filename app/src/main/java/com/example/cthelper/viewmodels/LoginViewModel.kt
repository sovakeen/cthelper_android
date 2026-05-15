package com.example.cthelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.data.local.TokenManager
import com.example.cthelper.data.remote.AuthService
import com.example.cthelper.data.remote.models.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
             val response = authService.login(
                 LoginRequest(
                     email = email,
                     password = password,
                     clientType = 1,
                     ipAddress = "192.168.0.1",
                     deviceInfo = "{}",
                     deviceId = "test"
                 )
             )
             if (response.isSuccessful) {
                 response.body()?.let {
                     print("accessToken: ${it.accessToken}")
                     tokenManager.saveAccessToken(it.accessToken)
                 }
             }
        }
    }
}
