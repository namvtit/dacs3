package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckScreen(
    onNavigateToQuiz: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToUser: () -> Unit
) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToQuiz, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Quiz")
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onNavigateToHome, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Home")
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onNavigateToUser, modifier = Modifier.fillMaxWidth()) {
            Text("Go to User")
        }
    }
}
