//package com.example.cthelper.ui.test
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import com.example.cthelper.domain.model.Test
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//
//sealed interface TestSolvingUiState {
//    data class Success(
//        val prikol: Int
//    ): TestSolvingUiState
//    object Loading: TestSolvingUiState
//    data class Error(
//        val msg: String
//    ): TestSolvingUiState
//}
//
//@HiltViewModel
//class TestSolvingViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//    private val _uiState = MutableStateFlow<TestSolvingUiState>(TestSolvingUiState.Loading)
//    val uiState: StateFlow<TestSolvingUiState> = _uiState.asStateFlow()
//
//    init {
//        val testId: Int? = savedStateHandle.get<Int>("testId")
//        testId?.let { loadTest(it) }
//    }
//
//    private fun loadTest(testId: Long) {
//        _uiState.value = _uiState.value.copy(isLoading = true)
//        val test = SampleTestsData.getTestById(testId)
//        if (test != null) {
//            _uiState.value = _uiState.value.copy(test = test, isLoading = false)
//        } else {
//            _uiState.value = _uiState.value.copy(error = "Test not found", isLoading = false)
//        }
//    }
//
//    fun submitAnswer(problemId: Long, answer: String) {
//        val newAnswers = _uiState.value.answers.toMutableMap()
//        newAnswers[problemId] = answer
//        _uiState.value = _uiState.value.copy(answers = newAnswers)
//    }
//
//    fun nextProblem() {
//        val currentState = _uiState.value
//        if (currentState.test != null && currentState.currentProblemIndex < currentState.test.problemCount - 1) {
//            _uiState.value = currentState.copy(currentProblemIndex = currentState.currentProblemIndex + 1)
//        }
//    }
//}
