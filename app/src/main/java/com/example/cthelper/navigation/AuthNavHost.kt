package com.example.cthelper.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cthelper.feature.common.login.LoginScreen
import com.example.cthelper.feature.common.registration.RegistrationScreen
import com.example.cthelper.root.UserRole

@Composable
fun AuthNavHost(
    onLoginSuccess: (UserRole) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AuthDestinations.LOGIN.label) {
        composable(AuthDestinations.LOGIN.label) {
            LoginScreen(
                navigateToRegistration = { navController.navigate(AuthDestinations.REGISTRATION.label) },
                onLoginSuccess = onLoginSuccess
            )
        }

        composable("registration") {
            RegistrationScreen(
                navigateToLogin = { navController.navigate(AuthDestinations.LOGIN.label) }
            )
        }
    }
}

enum class AuthDestinations(
    val label: String,
) {
    REGISTRATION("registration"),
    LOGIN("login")
}
