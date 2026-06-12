package com.example.cthelper.feature.student.attemptslist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cthelper.network.model.TestAttempt

@Composable
fun AttemptCard(
    testAttempt: TestAttempt,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {  },
) {
    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.small)
            .padding(16.dp)
            .clickable { onClick(testAttempt.testAttemptId) }
    ) {
        Text(text = testAttempt.testName, style = MaterialTheme.typography.titleMedium)
        Text(text = "Status: ${testAttempt.status}", style = MaterialTheme.typography.bodyMedium)
//        Text(text = "Author: ${test.authorName}", style = MaterialTheme.typography.bodyMedium)
//        Text(text = "Difficulty: ${test.avgDifficulty}", style = MaterialTheme.typography.bodySmall)
    }
}
