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
import com.example.dac.viewmodel.LearnViewModel

@Composable
fun LearnScreen(
    onClose: () -> Unit,
    viewModel: LearnViewModel = viewModel()
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                uiState.loading -> CircularProgressIndicator()
                uiState.error != null -> Text(uiState.error ?: "Lỗi", color = MaterialTheme.colorScheme.error)
                uiState.vocabularies.isEmpty() -> Text("Không còn từ mới")
                uiState.currentIndex >= uiState.vocabularies.size -> Text("Đã học xong 10 từ mới!")
                else -> {
                    val vocab = uiState.vocabularies[uiState.currentIndex]
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
                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = { viewModel.nextVocabulary() },
                        enabled = uiState.currentIndex < uiState.vocabularies.size
                    ) {
                        Text("Tiếp theo")
                    }
                }
            }
        }
    }
}