package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.dac.viewmodel.HomeViewModel

@Composable
fun PracticeScreen(
    userId: Int,
    homeViewModel: HomeViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        homeViewModel.loadReviewWords(userId)
    }

    val learnList by homeViewModel.learnList.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBack, modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Text("Practice Words: ${learnList.size} words", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            learnList.forEach { word ->
                Text(word, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
