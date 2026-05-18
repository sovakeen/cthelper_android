package com.example.cthelper.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.model.QuestionInstance
import com.example.cthelper.repository.TestAttemptRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TestSolvingUiState {
    val testId: Int

    data class Success(
        override val testId: Int,
        val questionInstances: List<QuestionInstance>
    ): TestSolvingUiState
    data class Loading(
        override val testId: Int = 0
    ): TestSolvingUiState
    data class Error(
        override val testId: Int,
        val msg: String
    ): TestSolvingUiState
}

@HiltViewModel
class TestSolvingViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val testManagementRepositoryImpl: TestAttemptRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow<TestSolvingUiState>(TestSolvingUiState.Loading())
    val uiState: StateFlow<TestSolvingUiState> = _uiState.asStateFlow()

    init {
        val testId: Int? = savedStateHandle.get<Int>("testId")
        testId?.let {
            _uiState.value = TestSolvingUiState.Loading(testId = it)
            loadTest(it)
        }
    }

    private fun loadTest(testId: Int) {
        viewModelScope.launch {
            _uiState.value = try {
                val response = testManagementRepositoryImpl.startAttempt(testId)
                TestSolvingUiState.Success(
                    testId,
                    response.questionInstances
                )
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "no_error_msg")
                TestSolvingUiState.Error(
                    testId,
                    e.message ?: "no_error_msg"
                )
            }
        }
    }

    fun onAnswerChanged(questionInstanceId: Int, newAnswer: String) {
        val currentState = _uiState.value
        if (currentState is TestSolvingUiState.Success) {
            val updatedList = currentState.questionInstances.map {
                if (it.questionInstanceId == questionInstanceId) {
                    it.copy(userAnswer = newAnswer)
                } else {
                    it
                }
            }
            _uiState.value = currentState.copy(questionInstances = updatedList)
        }
    }
}
