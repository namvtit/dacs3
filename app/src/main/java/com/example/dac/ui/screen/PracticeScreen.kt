package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dac.viewmodel.PracticeViewModel

@Composable
fun PracticeScreen(
    onClose: () -> Unit,
    viewModel: PracticeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
        Column(
            Modifier.align(Alignment.Center).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                uiState.loading -> CircularProgressIndicator()
                uiState.error != null -> Text(uiState.error ?: "Lỗi", color = MaterialTheme.colorScheme.error)
                uiState.finished || uiState.pairs.isEmpty() -> Text("Đã luyện tập xong!")
                else -> {
                    val (_, vocab) = uiState.pairs[uiState.currentIndex]
                    Text(
                        vocab.meaning.orEmpty(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(24.dp))
                    OutlinedTextField(
                        value = uiState.userInput,
                        onValueChange = { viewModel.onUserInputChange(it) },
                        label = { Text("Nhập từ tiếng Anh") }
                    )
                    Spacer(Modifier.height(16.dp))
                    if (!uiState.showResult) {
                        Button(
                            onClick = { viewModel.checkAnswer() },
                            enabled = uiState.userInput.isNotBlank()
                        ) {
                            Text("Kiểm tra")
                        }
                    } else {
                        if (uiState.isCorrect) {
                            Text("Chính xác!", color = MaterialTheme.colorScheme.primary)
                        } else {
                            Text("Sai! Đáp án: ${vocab.word}", color = MaterialTheme.colorScheme.error)
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.nextWord() }) {
                            Text("Từ tiếp theo")
                        }
                    }
                }
            }
        }
    }
}