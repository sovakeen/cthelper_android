package com.example.cthelper.feature.student.attemptslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.model.TestAttempt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AttemptsListUiState {
    data class Success(
        val testAttempts: List<TestAttempt>
    ): AttemptsListUiState
    object Loading: AttemptsListUiState
    data class Error(
        val msg: String
    ): AttemptsListUiState
}

@HiltViewModel
class AttemptsListViewModel @Inject constructor(
    val testAttemptRepositoryImpl: AttemptsListRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow<AttemptsListUiState>(AttemptsListUiState.Loading)
    val uiState: StateFlow<AttemptsListUiState> = _uiState.asStateFlow()

    init {
        loadAttemptsData()
    }

    fun loadAttemptsData() {
        viewModelScope.launch {
            _uiState.value = try {
                AttemptsListUiState.Success(testAttemptRepositoryImpl.getAttempts().attempts)
            } catch (e: Exception) {
                AttemptsListUiState.Error(e.message ?: "no_error_msg")
            }
        }
    }
}
