package com.example.cthelper.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.ui.util.TestAttemptCard
import com.example.cthelper.ui.util.TestCard
import com.example.cthelper.viewmodel.TestsManagementUiState
import com.example.cthelper.viewmodel.TestManagementViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestsManagementScreen(
    navigateToTestSolving: (Int) -> Unit,
    navigateToQR: () -> Unit // <-- Added navigation parameter
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tests", "TestAttempts")

    // State to handle the dropdown menu visibility
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("CT Helper") },
                    actions = {
                        Box {
                            IconButton(onClick = { menuExpanded = true }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Menu"
                                )
                            }
                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Scan QR Code") },
                                    onClick = {
                                        menuExpanded = false
                                        navigateToQR()
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier.statusBarsPadding() // Moved padding here
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTab
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> TestsManagementTab(navigateToTestSolving)
                1 -> TestAttemptsTab(navigateToReview = {})
            }
        }
    }
}

// ... [Keep TestsManagementTab and TestAttemptsTab the exact same] ...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestsManagementTab(
    navigateToTestSolving: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestManagementViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is TestsManagementUiState.Success -> LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                items((uiState as TestsManagementUiState.Success).tests) { item ->
                    TestCard(
                        test = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        onClick = navigateToTestSolving
                    )
                }
            }
            is TestsManagementUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is TestsManagementUiState.Error -> Text(
                text = "The following error occurred:\n${(uiState as TestsManagementUiState.Error).msg}",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestAttemptsTab(
    modifier: Modifier = Modifier,
    navigateToReview: (Int) -> Unit,
    viewModel: TestManagementViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is TestsManagementUiState.Success -> LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                items((uiState as TestsManagementUiState.Success).testAttempts) { item ->
                    TestAttemptCard(
                        testAttempt = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        onClick = {}
                    )
                }
            }
            is TestsManagementUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is TestsManagementUiState.Error -> Text(
                text = "The following error occurred:\n${(uiState as TestsManagementUiState.Error).msg}",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}
