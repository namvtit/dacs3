package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Sign Up", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") }
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    // TODO: gọi API đăng ký tại đây, thành công thì gọi onSignUpSuccess()
                    onSignUpSuccess()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Login")
            }
        }
    }
}