package com.example.vocabapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocabapp.viewmodel.HomeViewModel

@Composable
fun HomeTab(
    onNavigateToLearn: () -> Unit,
    onNavigateToPractice: () -> Unit,
    onNavigateToCheck: () -> Unit,
    onNavigateToUser: () -> Unit,
    viewModel: HomeViewModel = HomeViewModel()
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onNavigateToLearn, modifier = Modifier.weight(1f)) {
                Text("Learn New")
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = onNavigateToPractice, modifier = Modifier.weight(1f)) {
                Text("Practice")
            }
        }
        Spacer(Modifier.height(32.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedButton(onClick = onNavigateToCheck) { Text("Go to Check") }
            OutlinedButton(onClick = onNavigateToUser) { Text("Go to User") }
        }
    }
}