package com.example.cthelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cthelper.ui.LoginScreen
import com.example.cthelper.ui.QRScreen
import com.example.cthelper.ui.RegistrationScreen
import com.example.cthelper.ui.TestSolvingScreen
import com.example.cthelper.ui.TestsManagementScreen

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
                navigateToTests = { navController.navigate(AppDestinations.TESTS_MANAGEMENT.label) },
                navigateToQR = { navController.navigate(AppDestinations.QR_CODE.label) }
            )
        }

        composable(route = AppDestinations.TESTS_MANAGEMENT.label) {
            TestsManagementScreen(
                navigateToTestSolving = { testId ->
                    navController.navigate("${AppDestinations.TEST_SOLVING.label}/$testId")
                }
            )
        }

        @OptIn(androidx.camera.core.ExperimentalGetImage::class)
        composable(route = AppDestinations.QR_CODE.label) {
            QRScreen()
        }

        composable(
            route = "${AppDestinations.TEST_SOLVING.label}/{testId}",
            arguments = listOf(
                navArgument("testId") { type = NavType.IntType }
            )
        ) {
            TestSolvingScreen(
                navigateBack = { navController.popBackStack() },
                navigateToTests = { navController.navigate(AppDestinations.TESTS_MANAGEMENT.label) }
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
) {
    REGISTRATION("Registration"),
    LOGIN("Login"),
    TESTS_MANAGEMENT("TestManagement"),
    TEST_SOLVING("TestSolving"),
    QR_CODE("QRCode")
}
