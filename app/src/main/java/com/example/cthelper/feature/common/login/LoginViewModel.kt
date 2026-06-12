package com.example.cthelper.feature.common.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.root.TokenManager
import com.example.cthelper.root.UserRole
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
    val authRepositoryImpl: AuthRepositoryImpl,
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

    fun login(onLoginSuccess: (UserRole) -> Unit) {
        viewModelScope.launch {
            authRepositoryImpl.login(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
            val role = tokenManager.getUserRole() ?: "Student"
//            Log.e("INFO", role)
            if (role == "Student") onLoginSuccess(UserRole.STUDENT) else onLoginSuccess(UserRole.TEACHER)
        }
    }
}
