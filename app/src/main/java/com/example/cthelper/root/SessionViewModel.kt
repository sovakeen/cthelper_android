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
    // Inject your AuthRepository or DataStore here later to check saved tokens!
) : ViewModel() {

    // App starts in a loading state while we check for saved tokens
    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Loading)
    val sessionState: StateFlow<SessionState> = _sessionState.asStateFlow()

    init {
        checkExistingSession()
    }

    private fun checkExistingSession() {
        viewModelScope.launch {
            // TODO: Check SharedPreferences/DataStore for a saved token
            delay(1000) // Simulating a splash screen load

            // For now, default to logged out:
            _sessionState.value = SessionState.Unauthenticated
        }
    }

    // Call this from your LoginScreen when the API returns success!
    fun onLoginSuccess(role: UserRole) {
        _sessionState.value = SessionState.Authenticated(role)
    }

    // Call this from a "Logout" button anywhere in the app
    fun logout() {
        // TODO: Clear saved tokens here
        _sessionState.value = SessionState.Unauthenticated
    }
}
