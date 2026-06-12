//package com.example.cthelper
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.cthelper.feature.common.login.LoginScreen
//import com.example.cthelper.feature.student.teachers.QRScreen
//import com.example.cthelper.feature.common.registration.RegistrationScreen
//import com.example.cthelper.feature.student.testsolving.TestSolvingScreen
//import com.example.cthelper.feature.student.testslist.TestsListScreen
////import com.example.cthelper.viewmodel.TestReviewUiState
////import com.example.cthelper.viewmodel.TestReviewViewModel
//
//@Composable
//fun CTHelperNavHost(
//    modifier: Modifier = Modifier,
//    navController: NavHostController = rememberNavController()
//) {
//    NavHost(
//        navController = navController,
//        startDestination = AppDestinations.LOGIN.label,
//        modifier = modifier
//    ) {
//        composable(route = AppDestinations.REGISTRATION.label) {
//            RegistrationScreen(
//                navigateToLogin = { navController.navigate(AppDestinations.LOGIN.label) }
//            )
//        }
//
//        composable(route = AppDestinations.LOGIN.label) {
//            LoginScreen(
//                navigateToRegistration = { navController.navigate(AppDestinations.REGISTRATION.label) },
////                navigateToTests = { navController.navigate(AppDestinations.TESTS_MANAGEMENT.label) },
////                navigateToQR = { navController.navigate(AppDestinations.QR_CODE.label) }
//            )
//        }
//
//        composable(route = AppDestinations.TESTS_MANAGEMENT.label) {
//            TestsListScreen(
//                navigateToTestSolving = { testId ->
//                    navController.navigate("${AppDestinations.TEST_SOLVING.label}/$testId")
//                },
//                // Pass the QR navigation lambda here
//                navigateToQR = { navController.navigate(AppDestinations.QR_CODE.label) }
//            )
//        }
//
////        @OptIn(androidx.camera.core.ExperimentalGetImage::class)
//        composable(route = AppDestinations.QR_CODE.label) {
//            QRScreen()
//        }
//
//        composable(
//            route = "${AppDestinations.TEST_SOLVING.label}/{testId}",
//            arguments = listOf(
//                navArgument("testId") { type = NavType.IntType }
//            )
//        ) {
//            TestSolvingScreen(
////                navigateBack = { navController.popBackStack() },
//                navigateToTestsList = { navController.navigate(AppDestinations.TESTS_MANAGEMENT.label) }
//            )
//        }
//
////        composable(
////            route = "${AppDestinations.TEST_REVIEW.label}/{attemptId}",
////            arguments = listOf(navArgument("attemptId") { type = NavType.IntType })
////        ) {
////            val viewModel: TestReviewViewModel = hiltViewModel()
////            val uiState by viewModel.uiState.collectAsState()
////
////            when (val state = uiState) {
////                is TestReviewUiState.Loading -> {
////                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
////                        CircularProgressIndicator()
////                    }
////                }
////                is TestReviewUiState.Error -> {
////                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
////                        Text(text = "Error: ${state.msg}")
////                    }
////                }
////                is TestReviewUiState.Success -> {
////                    // Your unmodified UI screen!
////                    TestReviewScreen(
////                        testAttempt = state.testAttempt,
////                        onBack = { navController.popBackStack() }
////                    )
////                }
////            }
////        }
//    }
//}
//
//enum class AppDestinations(
//    val label: String,
//) {
//    REGISTRATION("Registration"),
//    LOGIN("Login"),
//    TESTS_MANAGEMENT("TestManagement"),
//    TEST_SOLVING("TestSolving"),
//    TEST_REVIEW("TestReview"),
//    QR_CODE("QRCode")
//}
