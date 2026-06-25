//package com.example.cthelper.feature.student.statistics
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.KeyboardArrowUp
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.LinearProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.cthelper.theme.CTHelperTheme
//import org.json.JSONObject
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StudentStatsScreen(
//    statsJson: JSONObject,
//    modifier: Modifier = Modifier
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "My Statistics") }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { }
//            ) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Submit")
//            }
//        },
//        modifier = modifier.fillMaxSize()
//    ) { innerPadding ->
//        LazyColumn(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(24.dp)
//        ) {
//            item {
//                OverallMetricsRow(statsJson)
//            }
//            item {
//                TopicPerformanceSection(statsJson)
//            }
//            item {
//                RecommendationsSection(statsJson)
//            }
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun OverallMetricsRow(statsJson: JSONObject) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        MetricCard(
//            label = "Common Rate",
//            value = "${statsJson.getInt("commonRate")}%",
//            icon = Icons.Default.KeyboardArrowUp,
//            color = MaterialTheme.colorScheme.primaryContainer,
//            modifier = Modifier.weight(1f)
//        )
//        MetricCard(
//            label = "Correct",
//            value = "${statsJson.getInt("correctAnswers")}/${statsJson.getInt("totalAnswers")}",
//            icon = Icons.Default.Star,
//            color = MaterialTheme.colorScheme.secondaryContainer,
//            modifier = Modifier.weight(1f)
//        )
//        MetricCard(
//            label = "Attempts",
//            value = statsJson.getInt("totalAttempts").toString(),
//            icon = Icons.Default.Info,
//            color = MaterialTheme.colorScheme.tertiaryContainer,
//            modifier = Modifier.weight(1f)
//        )
//    }
//}
//
//@Composable
//fun MetricCard(
//    label: String,
//    value: String,
//    icon: ImageVector,
//    color: Color,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier,
//        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.4f))
//    ) {
//        Column(
//            modifier = Modifier.padding(12.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = null,
//                modifier = Modifier.size(20.dp),
//                tint = MaterialTheme.colorScheme.onSurface
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = value,
//                style = MaterialTheme.typography.titleMedium,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = label,
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        }
//    }
//}
//
//@Composable
//fun TopicPerformanceSection(statsJson: JSONObject) {
//    val topics = statsJson.getJSONArray("statisticsByTopicList")
//
//    Column {
//        Text(
//            text = "Performance by Topic",
//            style = MaterialTheme.typography.titleLarge,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//
//        for (i in 0 until topics.length()) {
//            val topic = topics.getJSONObject(i)
//            TopicPerformanceItem(
//                name = topic.getString("topicName"),
//                rate = topic.getInt("averageSuccessRate")
//            )
//            if (i < topics.length() - 1) {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun TopicPerformanceItem(name: String, rate: Int) {
//    val color = when {
//        rate >= 80 -> Color(0xFF4CAF50) // Green
//        rate >= 50 -> Color(0xFFFFC107) // Yellow
//        else -> Color(0xFFF44336) // Red
//    }
//
//    Column {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = name,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.weight(1f)
//            )
//            Text(
//                text = "$rate%",
//                style = MaterialTheme.typography.labelLarge,
//                fontWeight = FontWeight.Bold,
//                color = color
//            )
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//        LinearProgressIndicator(
//            progress = { rate / 100f },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(8.dp)
//                .clip(RoundedCornerShape(4.dp)),
//            color = color,
//            trackColor = color.copy(alpha = 0.2f)
//        )
//    }
//}
//
//@Composable
//fun RecommendationsSection(statsJson: JSONObject) {
//    val pendingTopics = statsJson.getJSONArray("pendingTopicList")
//
//    Column {
//        Text(
//            text = "Recommended for Repetition",
//            style = MaterialTheme.typography.titleLarge,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            colors = CardDefaults.cardColors(
//                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
//            )
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                // Show first 5 pending topics for dummy screen
//                for (i in 0 until minOf(5, pendingTopics.length())) {
//                    val topic = pendingTopics.getJSONObject(i)
//                    RecommendationItem(topic.getString("topicName"))
//                    if (i < 4) {
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun RecommendationItem(name: String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Box(
//            modifier = Modifier
//                .size(6.dp)
//                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(3.dp))
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text = name, style = MaterialTheme.typography.bodySmall)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun StudentStatsScreenPreview() {
//    val jsonString = """
//    {
//      "fromDate": null,
//      "toDate": null,
//      "commonRate": 73,
//      "medianRate": 73,
//      "totalAnswers": 90,
//      "correctAnswers": 66,
//      "totalAttempts": 15,
//      "statisticsByTopicList": [
//        {
//          "topicId": 1,
//          "topicName": "Проверяемые безударные гласные в корне",
//          "averageSuccessRate": 73,
//          "medianSuccessRate": 85
//        },
//        {
//          "topicId": 2,
//          "topicName": "Непроверяемые гласные в корне",
//          "averageSuccessRate": 88,
//          "medianSuccessRate": 100
//        },
//        {
//          "topicId": 3,
//          "topicName": "Чередующиеся гласные о–а",
//          "averageSuccessRate": 45,
//          "medianSuccessRate": 83
//        },
//        {
//          "topicId": 4,
//          "topicName": "Чередующиеся гласные е–и",
//          "averageSuccessRate": 73,
//          "medianSuccessRate": 72
//        }
//      ],
//      "pendingTopicList": [
//        { "topicId": 5, "topicName": "О, Е, Ё после шипящих" },
//        { "topicId": 6, "topicName": "О, Е после Ц" },
//        { "topicId": 7, "topicName": "Ы, И после Ц" },
//        { "topicId": 8, "topicName": "Правописание приставок" }
//      ]
//    }
//    """
//    val statsJson = JSONObject(jsonString)
//
//    CTHelperTheme {
//        StudentStatsScreen(statsJson = statsJson)
//    }
//}
