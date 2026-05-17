package com.example.cthelper.ui.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.domain.model.Test

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestsManagementScreen(
    navigateToTestSolving: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestManagementViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Tests Management") }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (uiState) {
                is TestManagementUiState.Success -> LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items((uiState as TestManagementUiState.Success).tests) { item ->
                        TestCard(
                            test = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = navigateToTestSolving
                        )
                    }
                }
                is TestManagementUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TestManagementUiState.Error -> Text(
                    text = "The following error occurred:\n${(uiState as TestManagementUiState.Error).msg}",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun TestCard(
    test: Test,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {  },
) {
    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.small)
            .padding(16.dp)
            .clickable { onClick(test.testId) }
    ) {
        Text(text = test.testName, style = MaterialTheme.typography.titleMedium)
        Text(text = "Author: ${test.authorName}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Difficulty: ${test.avgDifficulty}", style = MaterialTheme.typography.bodySmall)
    }
}
