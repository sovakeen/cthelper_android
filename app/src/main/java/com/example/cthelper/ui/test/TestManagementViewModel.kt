package com.example.cthelper.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.domain.model.Test
import com.example.cthelper.remote.repository.TestSearchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TestManagementUiState {
    data class Success(
        val tests: List<Test> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ): TestManagementUiState
    object Loading: TestManagementUiState
    data class Error(
        val msg: String
    ): TestManagementUiState
}

@HiltViewModel
class TestManagementViewModel @Inject constructor(
    val testSearchRepositoryImpl: TestSearchRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow<TestManagementUiState>(TestManagementUiState.Loading)
    val uiState: StateFlow<TestManagementUiState> = _uiState.asStateFlow()

    init {
        loadTests()
    }

    fun loadTests() {
        viewModelScope.launch {
            _uiState.value = try {
                TestManagementUiState.Success(testSearchRepositoryImpl.getTests() ?: emptyList())
            } catch (e: Exception) {
                TestManagementUiState.Error(e.message ?: "no_error_msg")
            }
        }
    }
}
