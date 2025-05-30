package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onLearn: () -> Unit,
    onPractice: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onLearn, modifier = Modifier.fillMaxWidth(0.7f)) {
            Text("Learn")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onPractice, modifier = Modifier.fillMaxWidth(0.7f)) {
            Text("Practice")
        }
    }
}