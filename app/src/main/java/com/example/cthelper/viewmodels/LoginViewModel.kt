package com.example.cthelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.data.local.TokenManager
import com.example.cthelper.data.remote.AuthService
import com.example.cthelper.data.remote.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "student1@student1.com",
    val password: String = "111111",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    val authService: AuthService,
    val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            val response = authService.login(
                LoginRequest(
                    email = _uiState.value.email,
                    password = _uiState.value.password,
                    clientType = 1,
                    ipAddress = "192.168.0.1",
                    deviceInfo = "{}",
                    deviceId = "string"
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
