package com.example.cthelper.feature.student.attemptreview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.api.AttemptDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AttemptReviewUiState {
    data object Loading : AttemptReviewUiState
    data class Success(val attemptDetails: AttemptDetailsResponse) : AttemptReviewUiState
    data class Error(val msg: String) : AttemptReviewUiState
}

@HiltViewModel
class AttemptReviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val attemptReviewRepositoryImpl: AttemptReviewRepositoryImpl,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AttemptReviewUiState>(AttemptReviewUiState.Loading)
    val uiState: StateFlow<AttemptReviewUiState> = _uiState.asStateFlow()

    init {
        val attemptId: Int? = savedStateHandle.get<Int>("attemptId")
        attemptId?.let { loadAttempt(it) }
    }

    private fun loadAttempt(attemptId: Int) {
        viewModelScope.launch {
            try {
                val attemptDetails = attemptReviewRepositoryImpl.getAttemptDetails(attemptId)

                _uiState.value = AttemptReviewUiState.Success(attemptDetails)
            } catch (e: Exception) {
                _uiState.value = AttemptReviewUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
