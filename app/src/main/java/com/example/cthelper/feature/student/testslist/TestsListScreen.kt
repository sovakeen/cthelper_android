package com.example.cthelper.feature.student.testslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestsListScreen(
    navigateToTestSolving: (Int) -> Unit,
    navigateToAttemptsList: () -> Unit = {},
    onLogout: () -> Unit = {},
    viewModel: TestsListViewModel = hiltViewModel()
) {
//    var selectedTab by remember { mutableIntStateOf(0) }
//    val tabs = listOf("Tests", "Test Attempts")
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("CTHelper") },
                    actions = {
                        Row {
                            IconButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Menu"
                                )
                            }

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
                                    text = { Text("History") },
                                    onClick = {
                                        menuExpanded = false
                                        navigateToAttemptsList()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Statistics") },
                                    onClick = {  }
                                )
                                DropdownMenuItem(
                                    text = { Text("Teachers") },
                                    onClick = {  }
                                )
                                DropdownMenuItem(
                                    text = { Text("Profile") },
                                    onClick = {  }
                                )
                                DropdownMenuItem(
                                    text = { Text("Log out") },
                                    onClick = {
                                        menuExpanded = false
                                        onLogout()
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .statusBarsPadding()
                )

//                PrimaryTabRow(
//                    selectedTabIndex = selectedTab
//                ) {
//                    tabs.forEachIndexed { index, title ->
//                        Tab(
//                            selected = selectedTab == index,
//                            onClick = { selectedTab = index },
//                            text = { Text(title) }
//                        )
//                    }
//                }
            }
        }
    ) { padding ->
        val uiState by viewModel.uiState.collectAsState()

        Box(
            modifier = Modifier
            .padding(padding)
            .fillMaxSize()
        ) {
            when (uiState) {
                is TestsListUiState.Success -> LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items((uiState as TestsListUiState.Success).tests) { item ->
                        TestCard(
                            test = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = navigateToTestSolving
                        )
                    }
                }
                is TestsListUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TestsListUiState.Error -> Text(
                    text = "The following error occurred:\n${(uiState as TestsListUiState.Error).msg}",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

//        Column(modifier = Modifier.padding(padding)) {
//            when (selectedTab) {
//                0 -> TestsManagementTab(navigateToTestSolving)
//                1 -> TestAttemptsTab(navigateToReview = {})
//            }
//        }
    }
}
