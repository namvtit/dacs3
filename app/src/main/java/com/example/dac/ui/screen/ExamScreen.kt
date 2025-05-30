package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dac.viewmodel.ExamViewModel

@Composable
fun ExamScreen(
    onQuiz: () -> Unit,
    viewModel: ExamViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Danh sách bài kiểm tra đã làm", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        when {
            uiState.loading -> Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            uiState.error != null -> Text(uiState.error ?: "Lỗi", color = MaterialTheme.colorScheme.error)
            uiState.tests.isEmpty() -> Text("Chưa có bài kiểm tra nào.")
            else -> {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState.tests) { test ->
                        ExamItem(test)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onQuiz, modifier = Modifier.fillMaxWidth(0.7f).align(Alignment.CenterHorizontally)) {
            Text("Quiz")
        }
    }
}

@Composable
fun ExamItem(test: com.example.dac.model.Test) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Ngày: ${test.date}")
            Text(text = "Điểm: ${test.score}")
        }
    }
}