package com.example.cthelper.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.navigation.AuthNavHost
import com.example.cthelper.navigation.StudentNavHost
import com.example.cthelper.navigation.TeacherNavHost

@Composable
fun RootApp(
    viewModel: SessionViewModel = hiltViewModel()
) {
    val sessionState by viewModel.sessionState.collectAsState()

    when (val state = sessionState) {
        is SessionState.Loading -> {
            // Show a splash screen or spinner while checking if user is already logged in
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is SessionState.Unauthenticated -> {
            AuthNavHost(
                onLoginSuccess = {
                    // TODO: Pass the actual role from your API response here
                    viewModel.onLoginSuccess(UserRole.STUDENT)
                }
            )
        }
        is SessionState.Authenticated -> {
            when (state.role) {
                UserRole.STUDENT -> StudentNavHost(onLogout = { viewModel.logout() })
                UserRole.TEACHER -> TeacherNavHost(onLogout = { viewModel.logout() })
            }
        }
    }
}
