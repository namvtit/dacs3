package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LearnScreen(onClose: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
        Box(Modifier.align(Alignment.Center)) {
            Text("Learn Screen", style = MaterialTheme.typography.headlineMedium)
        }
    }
}