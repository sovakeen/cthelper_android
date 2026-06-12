package com.example.cthelper.feature.student.testslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.model.Test
import com.example.cthelper.network.model.TestAttempt
import com.example.cthelper.feature.student.attemptslist.AttemptsListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TestsListUiState {
    data class Success(
        val tests: List<Test>,
    ): TestsListUiState
    object Loading: TestsListUiState
    data class Error(
        val msg: String
    ): TestsListUiState
}

@HiltViewModel
class TestsListViewModel @Inject constructor(
    val testRepositoryImpl: TestsListRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow<TestsListUiState>(TestsListUiState.Loading)
    val uiState: StateFlow<TestsListUiState> = _uiState.asStateFlow()

    init {
        loadTestsData()
    }

    fun loadTestsData() {
        viewModelScope.launch {
            _uiState.value = try {
                TestsListUiState.Success(testRepositoryImpl.getTests())
            } catch (e: Exception) {
                TestsListUiState.Error(e.message ?: "no_error_msg")
            }
        }
    }
}
