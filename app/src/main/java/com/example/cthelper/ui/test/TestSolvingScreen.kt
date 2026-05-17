package com.example.cthelper.ui.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.example.cthelper.domain.model.QuestionInstance
import com.example.cthelper.domain.model.QuestionType
import com.example.cthelper.domain.model.QuestionStatement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSolvingScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestSolvingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Solving test ${uiState.testId}") }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (uiState) {
                is TestSolvingUiState.Success -> LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items((uiState as TestSolvingUiState.Success).questionInstances) { item ->
                        QuestionCard(
                            questionInstance = item,
                            onAnswerChanged = { viewModel.onAnswerChanged(item.questionInstanceId, it) },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                is TestSolvingUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TestSolvingUiState.Error -> Text(
                    text = "The following error occurred:\n${(uiState as TestSolvingUiState.Error).msg}",
                    modifier = Modifier
                        .align(Alignment.Center)
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
    val statement = remember(questionInstance.statement) {
        QuestionStatement.parse(questionInstance.type, questionInstance.statement)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.small)
            .padding(16.dp)
    ) {
        Text(text = questionInstance.code, style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        when (statement) {
            is QuestionStatement.Choice -> {
                Text(text = statement.statement, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                if (questionInstance.type == QuestionType.SINGLE_CHOICE) {
                    SingleChoiceContent(
                        options = statement.options,
                        selectedOptionKey = questionInstance.userAnswer,
                        onOptionSelected = { onAnswerChanged(it.key) }
                    )
                } else {
                    MultipleChoiceContent(
                        options = statement.options,
                        selectedOptionKeys = questionInstance.userAnswer?.split(",")?.filter { it.isNotEmpty() } ?: emptyList(),
                        onOptionToggled = { key ->
                            val currentKeys = questionInstance.userAnswer?.split(",")?.filter { it.isNotEmpty() }?.toMutableList() ?: mutableListOf()
                            if (currentKeys.contains(key)) currentKeys.remove(key) else currentKeys.add(key)
                            onAnswerChanged(currentKeys.joinToString(","))
                        }
                    )
                }
            }
            is QuestionStatement.OpenEnded -> {
                Text(text = statement.statement, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                OpenEndedContent(
                    value = questionInstance.userAnswer ?: "",
                    placeholder = statement.placeholder,
                    onValueChange = onAnswerChanged
                )
            }
        }
    }
}

@Composable
fun SingleChoiceContent(
    options: List<QuestionStatement.Choice.Option>,
    selectedOptionKey: String?,
    onOptionSelected: (QuestionStatement.Choice.Option) -> Unit
) {
    Column(Modifier.selectableGroup()) {
        options.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .selectable(
                        selected = (option.key == selectedOptionKey),
                        onClick = { onOptionSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option.key == selectedOptionKey),
                    onClick = null
                )
                Text(
                    text = option.value,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleChoiceContent(
    options: List<QuestionStatement.Choice.Option>,
    selectedOptionKeys: List<String>,
    onOptionToggled: (String) -> Unit
) {
    Column {
        options.forEach { option ->
            val isChecked = selectedOptionKeys.contains(option.key)
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable { onOptionToggled(option.key) }
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = null
                )
                Text(
                    text = option.value,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun OpenEndedContent(
    value: String,
    placeholder: String?,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder?.let { { Text(it) } },
        modifier = Modifier.fillMaxWidth()
    )
}
