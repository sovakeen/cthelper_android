package com.example.cthelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cthelper.ui.auth.LoginScreen
import com.example.cthelper.ui.auth.RegistrationScreen
import com.example.cthelper.ui.test.TestSolvingScreen
import com.example.cthelper.ui.test.TestsManagementScreen

@Composable
fun CTHelperNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN.label,
        modifier = modifier
    ) {
        composable(route = AppDestinations.REGISTRATION.label) {
            RegistrationScreen(
                navigateToLogin = { navController.navigate(AppDestinations.LOGIN.label) }
            )
        }

        composable(route = AppDestinations.LOGIN.label) {
            LoginScreen(
                navigateToRegistration = { navController.navigate(AppDestinations.REGISTRATION.label) },
                navigateToTests = { navController.navigate(AppDestinations.TEST_MANAGEMENT.label) }
            )
        }

        composable(route = AppDestinations.TEST_MANAGEMENT.label) {
            TestsManagementScreen(
                navigateToTestSolving = { testId ->
                    navController.navigate("${AppDestinations.TEST_SOLVING.label}/$testId")
                }
            )
        }

        composable(
            route = "${AppDestinations.TEST_SOLVING.label}/{testId}",
            arguments = listOf(
                navArgument("testId") { type = NavType.IntType }
            )
        ) {
            TestSolvingScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
) {
    REGISTRATION("Registration"),
    LOGIN("Login"),
    TEST_MANAGEMENT("TestManagement"),
    TEST_SOLVING("TestSolving")
}
