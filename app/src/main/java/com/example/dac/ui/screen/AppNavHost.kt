package com.example.vocabapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.vocabapp.ui.screen.*

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onLoginSuccess = { navController.navigate("main") { popUpTo("login") { inclusive = true } } }) }
        composable("main") { MainScreen(navController) }
        composable("learn") { LearnScreen(onBack = { navController.popBackStack("main", false) }) }
        composable("practice") { PracticeScreen(onBack = { navController.popBackStack("main", false) }) }
        composable("quizz") { QuizzScreen(onBack = { navController.popBackStack("check", false) }) }
    }
}