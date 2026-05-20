package com.example.cthelper.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cthelper.model.QuestionInstance
import com.example.cthelper.model.enums.QuestionType
import com.example.cthelper.theme.CTHelperTheme
import com.example.cthelper.ui.util.QuestionCard
import com.example.cthelper.viewmodel.TestSolvingUiState
import com.example.cthelper.viewmodel.TestSolvingViewModel
import org.json.JSONObject

@Composable
fun TestSolvingScreen(
    navigateBack: () -> Unit,
    navigateToTests: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestSolvingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    TestSolvingContent(
        uiState = uiState,
        onAnswerChanged = { id, answer -> viewModel.onAnswerChanged(id, answer) },
        onSubmit = { viewModel.submitAttempt(navigateToTests) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSolvingContent(
    uiState: TestSolvingUiState,
    onAnswerChanged: (Int, String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Solving test ${uiState.testId}")
                            Text(
                                text = "14:59",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSubmit
            ) {
                Icon(Icons.Default.Done, contentDescription = "Submit")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is TestSolvingUiState.Success -> LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items(uiState.questionInstances) { item ->
                        QuestionCard(
                            questionInstance = item,
                            onAnswerChanged = { onAnswerChanged(item.questionInstanceId, it) },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }

                is TestSolvingUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TestSolvingUiState.Error -> Text(
                    text = "The following error occurred:\n${uiState.msg}",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestSolvingScreenPreview() {
    val testJsonString = """
    {"testId":13,"testName":"Русский язык - Орфография (Публичный тест)","attemptId":213,"status":1,"duration":0,"rawScore":null,"problems":[{"userAnswer":"","userAnswerId":1153,"code":"A1","type":1,"statement":"{\"statement\": \"В каком слове пропущена буква И?\", \"answer1\": \"пр...одолеть\", \"answer2\": \"пр...пятствие\", \"answer3\": \"пр...тендент\", \"answer4\": \"пр...зирать (не уважать)\", \"answer5\": \"пр...емник (продолжатель)\"}"},{"userAnswer":"","userAnswerId":1154,"code":"A2","type":2,"statement":"{\"statement\": \"Выберите слова, в которых пишется разделительный Ъ:\", \"answer1\": \"с...экономить\", \"answer2\": \"ад...ютант\", \"answer3\": \"с...узить\", \"answer4\": \"ин...екция\", \"answer5\": \"кон...юнктура\"}"},{"userAnswer":"","userAnswerId":1155,"code":"A3","type":1,"statement":"{\"statement\": \"В каком слове пишется Ь для обозначения мягкости?\", \"answer1\": \"ноч...\", \"answer2\": \"береч...\", \"answer3\": \"мел...\", \"answer4\": \"сплош...\", \"answer5\": \"замуж...\"}"},{"userAnswer":"","userAnswerId":1156,"code":"A4","type":2,"statement":"{\"statement\": \"Выберите слова, в которых пишется буква А в корне -лаг-/-лож-:\", \"answer1\": \"предл...жение\", \"answer2\": \"изл...гать\", \"answer3\": \"пол...жить\", \"answer4\": \"сл...гаемое\", \"answer5\": \"перел...жить\"}"},{"userAnswer":"","userAnswerId":1157,"code":"A5","type":2,"statement":"{\"statement\": \"Выберите слова с чередующейся гласной в корне (буква И):\", \"answer1\": \"соб...рать\", \" зам...реть\", \"выт...рать\", \"ст...реть\", \"отп...рать\"}"},{"userAnswer":"","userAnswerId":1158,"code":"B1","type":3,"statement":"{\"statement\": \"Вставьте пропущенную букву: об...езд\", \"placeholder\": \"Введите одну букву\"}"}]}
"""
    val testJson = JSONObject(testJsonString)
    val problems = testJson.getJSONArray("problems")
    val questionInstances = mutableListOf<QuestionInstance>()

    for (i in 0 until problems.length()) {
        val problem = problems.getJSONObject(i)
        val type = QuestionType.entries.find { problem.getInt("type") == it.value }
            ?: QuestionType.SINGLE_CHOICE
        questionInstances.add(
            QuestionInstance(
                questionInstanceId = problem.getInt("userAnswerId"),
                code = problem.getString("code"),
                type = type,
                statement = problem.getString("statement"),
                userAnswer = problem.optString("userAnswer", "")
            )
        )
    }

    val dummyUiState = TestSolvingUiState.Success(
        testId = 13,
        attemptId = 213,
        questionInstances = questionInstances
    )

    CTHelperTheme {
        TestSolvingContent(
            uiState = dummyUiState,
            onAnswerChanged = { _, _ -> },
            onSubmit = {}
        )
    }
}
