package com.example.cthelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cthelper.ui.screens.LoginScreen
import com.example.cthelper.ui.screens.RegistrationScreen

@Composable
fun CTHelperNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.REGISTRATION.label,
        modifier = modifier
    ) {
        composable(route = AppDestinations.REGISTRATION.label) {
            RegistrationScreen(
                navigateToLogin = { navController.navigate(AppDestinations.LOGIN.label) }
            )
        }
        composable(route = AppDestinations.LOGIN.label) {
            LoginScreen(
                navigateToRegistration = { navController.navigate(AppDestinations.REGISTRATION.label) }
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
//    val icon: Int,
) {
    REGISTRATION("Registration"),
    LOGIN("Login"),
}
