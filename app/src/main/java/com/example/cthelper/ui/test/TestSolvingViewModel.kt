//package com.example.cthelper.ui.test
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import com.example.cthelper.domain.sample.SampleTestsData
//import com.example.cthelper.domain.sample.TestData
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//
//data class TestSolvingUiState(
//    val test: TestData? = null,
//    val isLoading: Boolean = false,
//    val error: String? = null,
//    val currentProblemIndex: Int = 0,
//    val answers: Map<Long, String> = emptyMap()
//)
//
//@HiltViewModel
//class TestSolvingViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(TestSolvingUiState())
//    val uiState: StateFlow<TestSolvingUiState> = _uiState.asStateFlow()
//
//    init {
//        val testId: Long? = savedStateHandle.get<Long>("testId")
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
