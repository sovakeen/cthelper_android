package com.example.cthelper.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.util.composable.QuestionCard
import com.example.cthelper.viewmodel.TestSolvingUiState
import com.example.cthelper.viewmodel.TestSolvingViewModel

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
                title = { Text(text = "Solving test ${uiState.testId}") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.submitAttempt() }
            ) {
                Icon(Icons.Default.Done, contentDescription = "Submit")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (uiState) {
                is TestSolvingUiState.Success -> LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items((uiState as TestSolvingUiState.Success).questionInstances) { item ->
                        QuestionCard(
                            questionInstance = item,
                            onAnswerChanged = { viewModel.onAnswerChanged(item.questionInstanceId, it) },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                is TestSolvingUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TestSolvingUiState.Error -> Text(
                    text = "The following error occurred:\n${(uiState as TestSolvingUiState.Error).msg}",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
