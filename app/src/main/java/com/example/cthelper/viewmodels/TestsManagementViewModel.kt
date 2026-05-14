package com.example.cthelper.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cthelper.data.local.sample.SampleTestsData
import com.example.cthelper.data.local.sample.TestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class TestsManagementUiState(
    val tests: List<TestData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TestsManagementViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TestsManagementUiState())
    val uiState: StateFlow<TestsManagementUiState> = _uiState.asStateFlow()

    init {
        loadTests()
    }

    private fun loadTests() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Simulate loading from sample data
        _uiState.value = _uiState.value.copy(
            tests = SampleTestsData.allTests,
            isLoading = false
        )
    }
}
