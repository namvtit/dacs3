// ui/screen/UserScreen.kt
package com.example.dac.ui.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun UserScreen(
    userName: String = "User Name",
    levelCounts: List<Int> = listOf(300, 100, 250, 120, 60), // Mock data, thay bằng viewModel nếu có
    totalWords: Int = 2000
) {
    val levelColors = listOf(
        Brush.horizontalGradient(listOf(Color(0xFF00B4D8), Color(0xFF48CAE4))), // Level 1: Blue
        Brush.horizontalGradient(listOf(Color(0xFF5E60CE), Color(0xFF5390D9))), // Level 2: Violet
        Brush.horizontalGradient(listOf(Color(0xFF43AA8B), Color(0xFF90BE6D))), // Level 3: Green
        Brush.horizontalGradient(listOf(Color(0xFFF9C74F), Color(0xFFF9844A))), // Level 4: Yellow-Orange
        Brush.horizontalGradient(listOf(Color(0xFFF3722C), Color(0xFFEE4266)))  // Level 5: Red-Pink
    )
    val levelLabels = listOf("Level 1", "Level 2", "Level 3", "Level 4", "Level 5")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "Your Vocabulary Progress",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        levelCounts.forEachIndexed { i, count ->
            ProgressBarLevel(
                label = levelLabels[i],
                progress = count / totalWords.toFloat(),
                count = count,
                total = totalWords,
                brush = levelColors[i]
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProgressBarLevel(
    label: String,
    progress: Float,
    count: Int,
    total: Int,
    brush: Brush
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$count/$total",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
                .background(Color(0xFFF3F5FA), shape = RoundedCornerShape(18.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .background(brush, shape = RoundedCornerShape(18.dp))
            )
        }
    }
}
