package com.example.vocabapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Check", "User")
    val icons = listOf(Icons.Default.Home, Icons.Default.Check, Icons.Default.Person)

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = title) },
                        label = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> HomeTab(
                    onNavigateToLearn = { navController.navigate("learn") },
                    onNavigateToPractice = { navController.navigate("practice") },
                    onNavigateToCheck = { selectedTab = 1 },
                    onNavigateToUser = { selectedTab = 2 }
                )
                1 -> CheckTab(
                    onNavigateToQuizz = { navController.navigate("quizz") },
                    onNavigateToHome = { selectedTab = 0 },
                    onNavigateToUser = { selectedTab = 2 }
                )
                2 -> UserTab(
                    onNavigateToHome = { selectedTab = 0 },
                    onNavigateToCheck = { selectedTab = 1 }
                )
            }
        }
    }
}