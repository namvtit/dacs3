package com.example.dac.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(val route: String, val label: String, val icon: ImageVector) {
    object Home : MainScreen("home", "Home", Icons.Filled.Home)
    object Check : MainScreen("check", "Check", Icons.Filled.CheckCircle)
    object User : MainScreen("user", "User", Icons.Filled.Person)
}

@Composable
fun MainTabNavHost(
    navController: NavController,
    userId: Int,
    homeViewModel: com.example.dac.viewmodel.HomeViewModel
) {
    val bottomNavController = rememberNavController()
    val tabs = listOf(MainScreen.Home, MainScreen.Check, MainScreen.User)

    Scaffold(
        bottomBar = {
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            NavigationBar {
                tabs.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                bottomNavController.navigate(screen.route) {
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = MainScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainScreen.Home.route) {
                HomeScreen(
                    userId = userId,
                    homeViewModel = homeViewModel,
                    onNavigateToLearnNew = { navController.navigate("learnnew") },
                    onNavigateToPractice = { navController.navigate("practice") }
                )
            }
            composable(MainScreen.Check.route) {
                CheckScreen(
                    onNavigateToQuiz = { navController.navigate("quiz") },
                    onNavigateToHome = { bottomNavController.navigate(MainScreen.Home.route) },
                    onNavigateToUser = { bottomNavController.navigate(MainScreen.User.route) }
                )
            }
            composable(MainScreen.User.route) {
                UserScreen()
            }
        }
    }
}
