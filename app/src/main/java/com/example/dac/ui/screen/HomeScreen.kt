package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dac.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    userId: Int,
    homeViewModel: HomeViewModel,
    onNavigateToLearnNew: () -> Unit,
    onNavigateToPractice: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LearnOptionCard(
            title = "Learn New",
            description = "Start learning new vocabulary now!",
            icon = Icons.Filled.School,
            bgColor = Color(0xFFCAF0F8),
            onClick = onNavigateToLearnNew
        )
        Spacer(Modifier.height(28.dp))
        LearnOptionCard(
            title = "Practice",
            description = "Review words you have learned",
            icon = Icons.Filled.Refresh,
            bgColor = Color(0xFFFFE5D9),
            onClick = onNavigateToPractice
        )
    }
}

@Composable
fun LearnOptionCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    bgColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = Color(0xFF0077B6))
            Spacer(Modifier.width(22.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(6.dp))
                Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}
