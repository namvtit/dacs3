package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen(
    onLogout: (() -> Unit)? = null,
    viewModel: UserViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val maxPerLevel = 50
    val levels = listOf("A1", "A2", "B1", "B2", "C1", "C2")

    // Tải dữ liệu khi lần đầu vào screen
    LaunchedEffect(Unit) {
        viewModel.loadUserData()
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Xin chào, ${uiState.userName}!", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            when {
                uiState.loading -> CircularProgressIndicator()
                uiState.error != null -> Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
                else -> {
                    levels.forEach { level ->
                        val value = uiState.levelCounts[level] ?: 0
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                            Text("$level: $value/$maxPerLevel")
                            LinearProgressIndicator(
                                progress = (value / maxPerLevel.toFloat()).coerceAtMost(1f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(16.dp)
                            )
                            Spacer(Modifier.height(12.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    onLogout?.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text("Logout")
            }
        }
    }
}