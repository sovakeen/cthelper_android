package com.example.cthelper.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.model.QuestionInstanceExt
import com.example.cthelper.network.model.TestAttempt
import com.example.cthelper.network.model.enums.AttemptStatus
import com.example.cthelper.network.model.enums.QuestionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

sealed interface AttemptReviewUiState {
    data object Loading : AttemptReviewUiState
    data class Success(val testAttempt: TestAttempt) : AttemptReviewUiState
    data class Error(val msg: String) : AttemptReviewUiState
}

@HiltViewModel
class AttemptReviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
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
                // Fetch the raw JSON string
                val rawJsonString = api.getAttemptRaw(attemptId).string()

                // Parse it on the fly using your exact logic
                val json = JSONObject(rawJsonString)
                val userAnswersJson = json.getJSONArray("userAnswers")
                val userAnswers = mutableListOf<QuestionInstanceExt>()

                for (i in 0 until userAnswersJson.length()) {
                    val obj = userAnswersJson.getJSONObject(i)
                    userAnswers.add(
                        QuestionInstanceExt(
                            questionInstanceId = obj.getInt("problemId"),
                            isActualQuestionInstance = obj.getBoolean("isActualProblemVersion"),
                            statement = obj.getString("statement"),
                            userAnswer = obj.optString("answer", ""),
                            isCorrect = obj.getBoolean("isCorrect"),
                            answer = obj.optString("correctAnswer", ""),
                            explanation = obj.optString("explanation", ""),
                            type = QuestionType.entries.find { it.value == obj.getInt("type") } ?: QuestionType.SINGLE_CHOICE,
                            difficulty = obj.optDouble("difficulty", 0.0),
                            topicName = obj.optString("topicName", "")
                        )
                    )
                }

                val attempt = TestAttempt(
                    testAttemptId = json.getInt("testAttemptId"),
                    testName = json.getString("testName"),
                    testId = json.getInt("testId"),
                    studentId = json.getInt("studentId"),
                    studentName = json.getString("studentName"),
                    status = AttemptStatus.COMPLETED,
                    duration = json.getInt("duration"),
                    rawScore = json.getInt("rawScore"),
                    createdAt = json.getString("createdAt"),
                    userAnswers = userAnswers
                )

                _uiState.value = AttemptReviewUiState.Success(attempt)
            } catch (e: Exception) {
                _uiState.value = AttemptReviewUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
