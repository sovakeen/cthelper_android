package com.example.cthelper.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cthelper.viewmodels.RegistrationViewModel

@Composable
fun RegistrationScreen(
    navigateToLogin: () -> Unit,
    viewModel: RegistrationViewModel,
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
                text = "Registration Screen"
            )
            Text(
                text = viewModel.sampleFun()
            )
            Button(
                onClick = {
                    navigateToLogin()
                }
            ) {
                Text(
                    text = "To Login"
                )
            }
        }
    }
}
