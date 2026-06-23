package com.example.cthelper.feature.student.attemptreview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.api.AttemptDetailsResponse
import com.example.cthelper.network.model.QuestionInstanceExt
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
        loadDummyAttempt()
    }

    private fun loadDummyAttempt() {
        val sampleJsonString = """
        {
          "testId": 10,
          "testName": "Русский язык - Орфография (Публичный тест)",
          "attemptId": 213,
          "status": 3,
          "duration": 89,
          "rawScore": 4,
          "studentId": 6,
          "studentName": "student_1",
          "createdAt": "2026-05-20T22:31:49.947432+00:00",
          "problems": [
            {
              "userAnswer": "5",
              "userAnswerId": 1153,
              "code": "A1",
              "type": 1,
              "statement": "{\"answer1\": \"ц...ркуль\", \"answer2\": \"ц...клон\", \"answer3\": \"акац...я\", \"answer4\": \"лекц...я\", \"answer5\": \"ц...плёнок\", \"statement\": \"В каком слове пишется ЦЫ?\"}",
              "isCorrect": true,
              "correctAnswer": "5",
              "explanation": "{\"explanation\": \"После Ц в корне слова пишется Ы только в исключениях: цыплёнок, цыган, цыц, на цыпочках.\"}",
              "topicName": "Правописание ЦЫ/ЦИ"
            },
            {
              "userAnswer": "12345",
              "userAnswerId": 1154,
              "code": "A2",
              "type": 2,
              "statement": "{\"answer1\": \"крыж...вник\", \"answer2\": \"капюш...н\", \"answer3\": \"ш...рох\", \"answer4\": \"ш...мпол\", \"answer5\": \"ч...порный\", \"statement\": \"Выберите слова с О после шипящих в корне (исключения):\"}",
              "isCorrect": true,
              "correctAnswer": "12345",
              "explanation": "{\"explanation\": \"Все эти слова - исключения, в которых после шипящих под ударением в корне пишется О.\"}",
              "topicName": "О/Ё после шипящих"
            },
            {
              "userAnswer": "4",
              "userAnswerId": 1155,
              "code": "A3",
              "type": 1,
              "statement": "{\"answer1\": \"беч...вка\", \"answer2\": \"трещ...тка\", \"answer3\": \"сгущ...нка\", \"answer4\": \"ноч...вка\", \"answer5\": \"коч...вка\", \"statement\": \"В каком слове пишется Ё?\"}",
              "isCorrect": false,
              "correctAnswer": "1",
              "explanation": "{\"explanation\": \"В корне слова под ударением пишется Ё, если в родственных словах или в другой форме того же слова пишется Е (бечёвка — бечева).\"}",
              "topicName": "О/Ё после шипящих"
            },
            {
              "userAnswer": "14",
              "userAnswerId": 1156,
              "code": "A4",
              "type": 2,
              "statement": "{\"answer1\": \"предл...жение\", \"answer2\": \"изл...гать\", \"answer3\": \"пол...жить\", \"answer4\": \"сл...гаемое\", \"answer5\": \"перел...жить\", \"statement\": \"Выберите слова, в которых пишется буква А в корне -лаг-/-лож-:\"}",
              "isCorrect": false,
              "correctAnswer": "24",
              "explanation": "{\"explanation\": \"В корне -лаг-/-лож- перед Г пишется А, перед Ж пишется О.\"}",
              "topicName": "Чередующиеся гласные"
            },
            {
              "userAnswer": "135",
              "userAnswerId": 1157,
              "code": "A5",
              "type": 2,
              "statement": "{\"answer1\": \"соб...рать\", \"answer2\": \"зам...реть\", \"answer3\": \"выт...рать\", \"answer4\": \"ст...реть\", \"answer5\": \"отп...рать\", \"statement\": \"Выберите слова с чередующейся гласной в корне (буква И):\"}",
              "isCorrect": true,
              "correctAnswer": "135",
              "explanation": "{\"explanation\": \"В корнях с чередованием Е/И пишется И, если после корня стоит суффикс -А-.\"}",
              "topicName": "Чередующиеся гласные"
            },
            {
              "userAnswer": "о",
              "userAnswerId": 1158,
              "code": "B1",
              "type": 3,
              "statement": "{\"statement\": \"Вставьте пропущенную букву: притв...риться (прикинуться)\", \"placeholder\": \"Введите одну букву\"}",
              "isCorrect": true,
              "correctAnswer": "о",
              "explanation": "{\"explanation\": \"В корне -твор-/-твар- под ударением пишется А, без ударения - О (исключение: утварь).\"}",
              "topicName": "Чередующиеся гласные"
            }
          ]
        }
        """
        
        try {
            val json = JSONObject(sampleJsonString)
            val problemsJson = json.getJSONArray("problems")
            val userAnswers = mutableListOf<QuestionInstanceExt>()

            for (i in 0 until problemsJson.length()) {
                val obj = problemsJson.getJSONObject(i)
                userAnswers.add(
                    QuestionInstanceExt(
                        questionInstanceId = obj.getInt("userAnswerId"),
                        isActualQuestionInstance = true,
                        statement = obj.getString("statement"),
                        userAnswer = obj.optString("userAnswer", ""),
                        isCorrect = obj.optBoolean("isCorrect", false),
                        answer = obj.optString("correctAnswer", ""),
                        explanation = obj.optString("explanation", ""),
                        type = QuestionType.entries.find { it.value == obj.getInt("type") } ?: QuestionType.SINGLE_CHOICE,
                        difficulty = obj.optDouble("difficulty", 3.0),
                        topicName = obj.optString("topicName", "")
                    )
                )
            }

            val attemptDetails = AttemptDetailsResponse(
                testAttemptId = json.getInt("attemptId"),
                testName = json.getString("testName"),
                testId = json.getInt("testId"),
                studentId = json.optInt("studentId", 6),
                studentName = json.optString("studentName", "student_1"),
                status = AttemptStatus.COMPLETED,
                duration = json.optInt("duration", 0),
                rawScore = json.optInt("rawScore", 0),
                createdAt = json.optString("createdAt", "2026-05-20T22:31:49.947432+00:00"),
                userAnswers = userAnswers
            )

            _uiState.value = AttemptReviewUiState.Success(attemptDetails)
        } catch (e: Exception) {
            _uiState.value = AttemptReviewUiState.Error(e.message ?: "Error parsing dummy data")
        }
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
