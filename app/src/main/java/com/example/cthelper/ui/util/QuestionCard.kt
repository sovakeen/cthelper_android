package com.example.cthelper.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import org.json.JSONObject
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cthelper.model.QuestionInstance
import com.example.cthelper.model.enums.QuestionType
import com.example.cthelper.theme.CTHelperTheme

val testJsonString = """
    {"testId":13,"testName":"Русский язык - Орфография (Публичный тест)","attemptId":213,"status":1,"duration":0,"rawScore":null,"problems":[{"userAnswer":"","userAnswerId":1153,"code":"A1","type":1,"statement":"{\"statement\": \"В каком слове пропущена буква И?\", \"answer1\": \"пр...одолеть\", \"answer2\": \"пр...пятствие\", \"answer3\": \"пр...тендент\", \"answer4\": \"пр...зирать (не уважать)\", \"answer5\": \"пр...емник (продолжатель)\"}"},{"userAnswer":"","userAnswerId":1154,"code":"A2","type":2,"statement":"{\"statement\": \"Выберите слова, в которых пишется разделительный Ъ:\", \"answer1\": \"с...экономить\", \"answer2\": \"ад...ютант\", \"answer3\": \"с...узить\", \"answer4\": \"ин...екция\", \"answer5\": \"кон...юнктура\"}"},{"userAnswer":"","userAnswerId":1155,"code":"A3","type":1,"statement":"{\"statement\": \"В каком слове пишется Ь для обозначения мягкости?\", \"answer1\": \"ноч...\", \"answer2\": \"береч...\", \"answer3\": \"мел...\", \"answer4\": \"сплош...\", \"answer5\": \"замуж...\"}"},{"userAnswer":"","userAnswerId":1156,"code":"A4","type":2,"statement":"{\"statement\": \"Выберите слова, в которых пишется буква А в корне -лаг-/-лож-:\", \"answer1\": \"предл...жение\", \"answer2\": \"изл...гать\", \"answer3\": \"пол...жить\", \"answer4\": \"сл...гаемое\", \"answer5\": \"перел...жить\"}"},{"userAnswer":"","userAnswerId":1157,"code":"A5","type":2,"statement":"{\"statement\": \"Выберите слова с чередующейся гласной в корне (буква И):\", \"answer1\": \"соб...рать\", \"answer2\": \"зам...реть\", \"answer3\": \"выт...рать\", \"answer4\": \"ст...реть\", \"answer5\": \"отп...рать\"}"},{"userAnswer":"","userAnswerId":1158,"code":"B1","type":3,"statement":"{\"statement\": \"Вставьте пропущенную букву: об...езд\", \"placeholder\": \"Введите одну букву\"}"}]}
"""
val testJson = JSONObject(testJsonString)
val problems = testJson.getJSONArray("problems")

@Preview
@Composable
fun Main() {
    CTHelperTheme() {
        Column() {
            for (i in 0 until problems.length()) {
                val problem: JSONObject = problems.getJSONObject(i)
                val type: QuestionType =
                    QuestionType.entries.find { problem.getInt("type") == it.value }
                        ?: QuestionType.SINGLE_CHOICE
                val testQuestionInstance = QuestionInstance(
                    questionInstanceId = i,
                    code = "A$i",
                    type = type,
                    statement = problem.getString("statement")
                )
                QuestionCard(
                    questionInstance = testQuestionInstance,
                    onAnswerChanged = {}
                )
            }
        }
    }
}

@Composable
fun QuestionCard(
    questionInstance: QuestionInstance,
    onAnswerChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val code = questionInstance.code
    val type = questionInstance.type
    val rootStatement = questionInstance.statement

    val statementJson = JSONObject(rootStatement)
    val statement = statementJson.getString("statement")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.small)
            .padding(16.dp)
    ) {
        Text(text = code, style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = statement, style = MaterialTheme.typography.titleMedium)
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("userAnswer: ${questionInstance.userAnswer}")

        when (type) {
            QuestionType.SINGLE_CHOICE -> {
                Column(Modifier.selectableGroup()) {
                    val keys = statementJson.keys()
                    while(keys.hasNext()) {
                        val key = keys.next()
                        val answerValue = statementJson.getString(key)
                        if (key.startsWith("answer")) {
                            val answerNumber = key.substringAfterLast("answer")
                            val isSelected = (answerNumber == questionInstance.userAnswer)

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .selectable(
                                        selected = isSelected,
                                        onClick = { onAnswerChanged(answerNumber) },
                                        role = Role.RadioButton
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = null
                                )
                                Text(
                                    text = answerValue,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }


                    }
                }
            }
            QuestionType.MULTIPLE_CHOICE -> {
                Column {
                    val keys = statementJson.keys()
                    while (keys.hasNext()) {
                        val key = keys.next()
                        val answerValue = statementJson.getString(key)
                        if (key.startsWith("answer")) {
                            val answerNumber = key.substringAfterLast("answer")
                            val isChecked = questionInstance.userAnswer.contains(answerNumber)

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .clickable { onAnswerChanged(answerNumber) }
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = null
                                )
                                Text(
                                    text = answerValue,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
            QuestionType.OPEN_ENDED -> {
                val placeholder = statementJson.getString("placeholder")

                TextField(
                    value = questionInstance.userAnswer,
                    onValueChange = onAnswerChanged,
                    placeholder = { Text(placeholder) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
