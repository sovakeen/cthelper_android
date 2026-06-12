package com.example.cthelper.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UserRole {
    STUDENT,
    TEACHER
}

sealed interface SessionState {
    data object Loading : SessionState
    data object Unauthenticated : SessionState
    data class Authenticated(val role: UserRole) : SessionState
}

@HiltViewModel
class SessionViewModel @Inject constructor(
//    tokenManager: TokenManager
) : ViewModel() {
    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Loading)
    val sessionState: StateFlow<SessionState> = _sessionState.asStateFlow()

    init {
        checkExistingSession()
    }

    private fun checkExistingSession() {
        viewModelScope.launch {
            // check for saved tokens
            delay(1000)

            _sessionState.value = SessionState.Unauthenticated
        }
    }

    fun onLoginSuccess(role: UserRole) {
        _sessionState.value = SessionState.Authenticated(role)
    }

    fun logout() {
        // clear saved tokens
        _sessionState.value = SessionState.Unauthenticated
    }
}
