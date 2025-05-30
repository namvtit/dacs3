package com.example.dac.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dac.ui.screen.*

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("main") { popUpTo("login") { inclusive = true } } },
                onSignUp = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                onSignUpSuccess = { navController.popBackStack("login", false) }
            )
        }
        composable("main") {
            MainScreen(navController)
        }
        composable("learn") {
            LearnScreen(onClose = { navController.popBackStack("main", false) })
        }
        composable("practice") {
            PracticeScreen(onClose = { navController.popBackStack("main", false) })
        }
        composable("quiz") {
            QuizScreen(onClose = { navController.popBackStack("main", false) })
        }
    }
}