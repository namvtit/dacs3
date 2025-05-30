package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.dac.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
                label = { Text("Full Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    errorMessage = null
                    loading = true
                    coroutineScope.launch {
                        try {
                            val response = RetrofitInstance.api.addUser(
                                username = username,
                                password = password,
                                name = name
                            )
                            loading = false
                            if (response.success) {
                                onSignUpSuccess()
                            } else {
                                errorMessage = response.error ?: "Đăng ký thất bại"
                            }
                        } catch (e: Exception) {
                            loading = false
                            errorMessage = "Không kết nối được server!"
                        }
                    }
                },
                enabled = !loading && name.isNotBlank() && username.isNotBlank() && password.isNotBlank(),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Sign Up")
                }
            }
            if (errorMessage != null) {
                Spacer(Modifier.height(8.dp))
                Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text("Back to Login")
            }
        }
    }
}