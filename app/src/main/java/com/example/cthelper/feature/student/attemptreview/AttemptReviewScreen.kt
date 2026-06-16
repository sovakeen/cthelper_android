package com.example.cthelper.feature.student.attemptreview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.network.api.AttemptDetailsResponse
import com.example.cthelper.network.model.QuestionInstanceExt
import com.example.cthelper.network.model.TestAttempt
import com.example.cthelper.network.model.enums.AttemptStatus
import com.example.cthelper.network.model.enums.QuestionType
import com.example.cthelper.theme.CTHelperTheme
import org.json.JSONObject
import kotlin.text.iterator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttemptReviewScreen(
    modifier: Modifier = Modifier,
    navigateToTestsList: () -> Unit = {},
    viewModel: AttemptReviewViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (uiState) {
                        is AttemptReviewUiState.Success -> { Text(text = (uiState as AttemptReviewUiState.Success).attemptDetails.testName) }
                        else -> { Text(text = "Loading") }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navigateToTestsList() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (uiState) {
                is AttemptReviewUiState.Success -> {
                    item {
                        ReviewSummary((uiState as AttemptReviewUiState.Success).attemptDetails)
                    }
                    items((uiState as AttemptReviewUiState.Success).attemptDetails.userAnswers) { answer ->
                        ReviewQuestionCard(answer)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                else -> item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewSummary(attemptDetails: AttemptDetailsResponse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Score",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${attemptDetails.rawScore} points",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Duration",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${attemptDetails.duration}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewQuestionCard(
    question: QuestionInstanceExt,
    modifier: Modifier = Modifier
) {
    val statementJson = JSONObject(question.statement)
    val statement = statementJson.getString("statement")
    
    val explanationText = if (!question.explanation.isNullOrBlank()) {
        JSONObject(question.explanation).optString("explanation", "")
    } else ""

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = if (question.isCorrect) Icons.Default.CheckCircle else Icons.Default.Clear,
                    contentDescription = if (question.isCorrect) "Correct" else "Incorrect",
                    tint = if (question.isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = question.topicName ?: "Question",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = statement, style = MaterialTheme.typography.bodyLarge)
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AnswerSection(
                label = "Your Answer:",
                answer = formatAnswer(question.userAnswer, statementJson, question.type),
                color = if (question.isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                textColor = if (question.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
            )
            
            if (!question.isCorrect) {
                Spacer(modifier = Modifier.height(8.dp))
                AnswerSection(
                    label = "Correct Answer:",
                    answer = formatAnswer(question.answer ?: "", statementJson, question.type),
                    color = Color(0xFFE8F5E9),
                    textColor = Color(0xFF2E7D32)
                )
            }
            
            if (explanationText.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "Explanation",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = explanationText,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerSection(
    label: String,
    answer: String,
    color: Color,
    textColor: Color
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = if (answer.isBlank()) "(Empty)" else answer,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

fun formatAnswer(answerCode: String, statementJson: JSONObject, type: QuestionType): String {
    if (answerCode.isBlank()) return ""
    return when (type) {
        QuestionType.SINGLE_CHOICE, QuestionType.MULTIPLE_CHOICE -> {
            val formatted = mutableListOf<String>()
            for (char in answerCode) {
                val key = "answer$char"
                if (statementJson.has(key)) {
                    formatted.add(statementJson.getString(key))
                } else {
                    formatted.add(char.toString())
                }
            }
            formatted.joinToString(", ")
        }
        QuestionType.OPEN_ENDED -> answerCode
    }
}

@Preview(showBackground = true)
@Composable
fun AttemptReviewScreenPreview() {
    val sampleJsonString = """
    {
      "testAttemptId": 214,
      "testName": "Русский язык - Орфография (Публичный тест)",
      "testId": 10,
      "studentId": 6,
      "studentName": "student_1",
      "status": 3,
      "duration": 32,
      "rawScore": 0,
      "createdAt": "2026-05-20T22:31:49.947432+00:00",
      "userAnswers": [
        {
          "problemId": 32,
          "isActualProblemVersion": true,
          "statement": "{\"statement\": \"В каком слове пишется ЦЫ?\", \"answer1\": \"ц...ркуль\", \"answer2\": \"ц...клон\", \"answer3\": \"акац...я\", \"answer4\": \"лекц...я\", \"answer5\": \"ц...плёнок\"}",
          "answer": "5",
          "isCorrect": true,
          "correctAnswer": "5",
          "explanation": "{\"explanation\": \"После Ц в корне слова пишется Ы только в исключениях: цыплёнок, цыган, цыц.\"}",
          "type": 1,
          "difficulty": 3,
          "topicName": "Проверяемые безударные гласные в корне"
        },
        {
          "problemId": 34,
          "isActualProblemVersion": true,
          "statement": "{\"statement\": \"Выберите слова с О после шипящих в корне (исключения):\", \"answer1\": \"крыж...вник\", \"answer2\": \"капюш...н\", \"answer3\": \"ш...рох\", \"answer4\": \"ш...мпол\", \"answer5\": \"ч...порный\"}",
          "answer": "12",
          "isCorrect": false,
          "correctAnswer": "12345",
          "explanation": "{\"explanation\": \"Все эти слова - исключения, в которых после шипящих под ударением пишется О.\"}",
          "type": 2,
          "difficulty": 4,
          "topicName": "Непроверяемые гласные в корне"
        },
        {
          "problemId": 38,
          "isActualProblemVersion": true,
          "statement": "{\"statement\": \"В каком слове пишется Ё?\", \"answer1\": \"беч...вка\", \"answer2\": \"трещ...тка\", \"answer3\": \"сгущ...нка\", \"answer4\": \"ноч...вка\", \"answer5\": \"коч...вка\"}",
          "answer": "",
          "isCorrect": false,
          "correctAnswer": "3",
          "explanation": "{\"explanation\": \"В отглагольных существительных после шипящих под ударением пишется Ё (сгущёнка - от сгустить).\"}",
          "type": 1,
          "difficulty": 3,
          "topicName": "Чередующиеся гласные е–и"
        }
      ]
    }
    """
    val json = JSONObject(sampleJsonString)
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

    CTHelperTheme {
        AttemptReviewScreen(
//            onBack = {}
        )
    }
}
