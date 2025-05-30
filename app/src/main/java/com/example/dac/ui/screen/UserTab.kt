package com.example.vocabapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun UserTab(
    onNavigateToHome: () -> Unit,
    onNavigateToCheck: () -> Unit
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("User Info", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onNavigateToHome) { Text("Back to Home") }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateToCheck) { Text("Go to Check") }
    }
}