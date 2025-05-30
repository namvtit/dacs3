package com.example.vocabapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocabapp.viewmodel.HomeViewModel

@Composable
fun LearnScreen(
    onBack: () -> Unit,
    viewModel: HomeViewModel = HomeViewModel()
) {
    val words = viewModel.loadNewWords()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learn New Words") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Home, contentDescription = "Back Home")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            words.forEach { word ->
                Card(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(word.word, style = MaterialTheme.typography.titleMedium)
                        Text(word.meaning, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}