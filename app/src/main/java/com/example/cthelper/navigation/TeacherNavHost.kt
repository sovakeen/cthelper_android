package com.example.cthelper.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TeacherNavHost(
    onLogout: () -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TeacherDestinations.INVITE_CODES.label) {
        composable(TeacherDestinations.INVITE_CODES.label) {
            // Placeholder for your Teacher UI
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = onLogout) { Text("Teacher Logout") }
            }
        }
    }
}

enum class TeacherDestinations(
    val label: String,
) {
    INVITE_CODES("invite_codes"),
    BINDING_REQUESTS("binding_requests"),
    STUDENTS("students")
}

