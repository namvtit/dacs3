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
import com.example.dac.viewmodel.QuizViewModel

@Composable
fun QuizScreen(
    onClose: () -> Unit,
    viewModel: QuizViewModel = viewModel()
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
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                uiState.loading -> CircularProgressIndicator()
                uiState.error != null -> Text(uiState.error ?: "Lỗi", color = MaterialTheme.colorScheme.error)
                uiState.finished || uiState.vocabularies.isEmpty() -> {
                    Text("Đã hoàn thành Quiz!", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(12.dp))
                    Text("Điểm của bạn: ${uiState.score}/${uiState.vocabularies.size}")
                }
                else -> {
                    val vocab = uiState.vocabularies[uiState.currentIndex]
                    Text(
                        "Nghĩa: ${vocab.meaning.orEmpty()}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(24.dp))

                    if (!uiState.showFlashCard) {
                        OutlinedTextField(
                            value = uiState.userInput,
                            onValueChange = { viewModel.onUserInputChange(it) },
                            label = { Text("Nhập từ tiếng Anh") },
                            singleLine = true
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.checkAnswer() },
                            enabled = uiState.userInput.isNotBlank()
                        ) {
                            Text("Kiểm tra")
                        }
                    } else {
                        FlashCard3D(
                            isBack = uiState.isBack,
                            word = vocab.word,
                            ipa = vocab.ipa.orEmpty(),
                            part = vocab.part.orEmpty(),
                            contextEnglish = vocab.context_english.orEmpty(),
                            level = vocab.level.orEmpty(),
                            meaning = vocab.meaning.orEmpty(),
                            contextVietnamese = vocab.context_vietnamese.orEmpty(),
                            onFlip = { viewModel.flipCard() }
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { viewModel.nextWord() }) {
                            Text("Từ tiếp theo")
                        }
                    }
                }
            }
        }
    }
}