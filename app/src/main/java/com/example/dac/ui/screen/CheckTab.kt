package com.example.vocabapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun CheckTab(
    onNavigateToQuizz: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToUser: () -> Unit
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onNavigateToQuizz) {
                Text("Quizz")
            }
            OutlinedButton(onClick = onNavigateToHome) {
                Text("Back to Home")
            }
            OutlinedButton(onClick = onNavigateToUser) {
                Text("Go to User")
            }
        }
    }
}