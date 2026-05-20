package com.example.cthelper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.model.Test
import com.example.cthelper.model.TestAttempt
import com.example.cthelper.repository.TestAttemptRepositoryImpl
import com.example.cthelper.repository.TestRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TestsManagementUiState {
    data class Success(
        val tests: List<Test>,
        val testAttempts: List<TestAttempt>
    ): TestsManagementUiState
    object Loading: TestsManagementUiState
    data class Error(
        val msg: String
    ): TestsManagementUiState
}

@HiltViewModel
class TestManagementViewModel @Inject constructor(
    val testRepositoryImpl: TestRepositoryImpl,
    val testAttemptRepositoryImpl: TestAttemptRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow<TestsManagementUiState>(TestsManagementUiState.Loading)
    val uiState: StateFlow<TestsManagementUiState> = _uiState.asStateFlow()

    init {
        loadTestsData()
    }

    fun loadTestsData() {
        viewModelScope.launch {
            _uiState.value = try {
                TestsManagementUiState.Success(testRepositoryImpl.getTests(), testAttemptRepositoryImpl.getAttempts().attempts)
            } catch (e: Exception) {
                TestsManagementUiState.Error(e.message ?: "no_error_msg")
            }
        }
    }
}
