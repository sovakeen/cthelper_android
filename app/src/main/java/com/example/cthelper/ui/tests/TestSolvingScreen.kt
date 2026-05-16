package com.example.cthelper.ui.tests

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSolvingScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestSolvingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.test?.testName ?: "Solving Test") }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text(text = uiState.error!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else if (uiState.test != null) {
                val currentProblemIndex = uiState.currentProblemIndex
                val test = uiState.test!!
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Problem ${currentProblemIndex + 1} of ${test.problemCount}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val problemMapping = test.problems.getOrNull(currentProblemIndex)
                    Text(
                        text = "Problem Code: ${problemMapping?.code ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    val currentAnswer = uiState.answers[problemMapping?.problemId] ?: ""
                    TextField(
                        value = currentAnswer,
                        onValueChange = { 
                            problemMapping?.problemId?.let { id ->
                                viewModel.submitAnswer(id, it)
                            }
                        },
                        label = { Text("Your Answer") }
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Button(
                        onClick = { viewModel.nextProblem() },
                        enabled = currentProblemIndex < test.problemCount - 1
                    ) {
                        Text(text = "Next Problem")
                    }
                    
                    if (currentProblemIndex == test.problemCount - 1) {
                        Button(onClick = navigateBack) {
                            Text(text = "Finish Test")
                        }
                    }
                }
            }
        }
    }
}
