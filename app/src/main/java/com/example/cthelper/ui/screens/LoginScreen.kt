package com.example.cthelper.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navigateToRegistration: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text(
                text = "Login Screen"
            )

            Button(
                onClick = {
                    navigateToRegistration()
                }
            ) {
                Text(
                    text = "To Registration"
                )
            }

            Button(
                onClick = {
                    viewModel.login(
                        email = "student1@student1.com",
                        password = "111111"
                    )
                }
            ) {
                Text(
                    text = "Emulate login"
                )
            }
        }
    }
}
