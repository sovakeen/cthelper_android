package com.example.cthelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cthelper.feature.student.attemptreview.AttemptReviewScreen
import com.example.cthelper.feature.student.attemptslist.AttemptsListScreen
import com.example.cthelper.feature.student.statistics.StatsScreen
import com.example.cthelper.feature.student.testslist.TestsListScreen
import com.example.cthelper.feature.student.testsolving.TestSolvingScreen

@Composable
fun StudentNavHost(onLogout: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StudentDestinations.TESTS_LIST.label) {
        composable(StudentDestinations.TESTS_LIST.label) {
            TestsListScreen(
                navigateToTestSolving = { testId -> navController.navigate("${StudentDestinations.TEST_SOLVING.label}/$testId") },
                navigateToAttemptsList = { navController.navigate(StudentDestinations.ATTEMPTS_LIST.label) },
                navigateToStats = { navController.navigate(StudentDestinations.STATS.label) },
                onLogout = { onLogout() }
            )
        }

        composable(
            route = "${StudentDestinations.TEST_SOLVING.label}/{testId}",
            arguments = listOf(navArgument("testId") { type = NavType.IntType })
        ) {
            TestSolvingScreen(
                navigateToTestsList = { navController.navigate(StudentDestinations.TESTS_LIST.label) }
            )
        }

        composable(
            route = StudentDestinations.ATTEMPTS_LIST.label
        ) {
            AttemptsListScreen(
                navigateToTestsList = { navController.navigate(StudentDestinations.TESTS_LIST.label) },
                navigateToReview = { attemptId -> navController.navigate("${StudentDestinations.ATTEMPT_REVIEW.label}/$attemptId") }
            )
        }

        composable(
            route = "${StudentDestinations.ATTEMPT_REVIEW.label}/{attemptId}",
            arguments = listOf(navArgument("attemptId") { type = NavType.IntType })
        ) {
            AttemptReviewScreen(
                navigateToTestsList = { navController.navigate(StudentDestinations.TESTS_LIST.label) }
            )
        }

        composable(
            route = "${StudentDestinations.STATS.label}"
        ) {
            StatsScreen(
//                navigateToTestsList = { navController.navigate(StudentDestinations.TESTS_LIST.label) }
            )
        }
    }
}

enum class StudentDestinations(
    val label: String,
) {
    TESTS_LIST("tests_list"),
    TEST_SOLVING("test_solving"),
    ATTEMPTS_LIST("attempts_list"),
    ATTEMPT_REVIEW("attempt_review"),
    STATS("statistics")
}
