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

    // Luôn load dữ liệu mới khi vào màn User
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
            Text(
                text = "Xin chào, ${uiState.userName}!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(24.dp))
            when {
                uiState.loading -> CircularProgressIndicator()
                uiState.error != null -> Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
                else -> {
                    LevelBar("Level 1", uiState.level1, maxPerLevel)
                    LevelBar("Level 2", uiState.level2, maxPerLevel)
                    LevelBar("Level 3", uiState.level3, maxPerLevel)
                    LevelBar("Level 4", uiState.level4, maxPerLevel)
                    LevelBar("Level 5", uiState.level5, maxPerLevel)
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onLogout?.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text("Logout")
            }
        }
    }
}

@Composable
fun LevelBar(label: String, value: Int, max: Int) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text("$label: $value/$max")
        LinearProgressIndicator(
            progress = { (value / max.toFloat()).coerceAtMost(1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Spacer(Modifier.height(12.dp))
    }
}