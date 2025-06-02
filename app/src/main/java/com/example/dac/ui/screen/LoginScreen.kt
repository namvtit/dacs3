package com.example.dac.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.example.dac.network.RetrofitInstance
import com.example.dac.model.UserSession
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit
) {
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
                    errorMessage = null
                    // Validate đầu vào trước khi gửi API
                    if (username.isBlank() || password.isBlank()) {
                        errorMessage = "Vui lòng nhập đầy đủ thông tin!"
                        return@Button
                    }
                    loading = true
                    coroutineScope.launch {
                        try {
                            val response = RetrofitInstance.api.getUser(username = username)
                            loading = false
                            if (response.success && response.user != null) {
                                if (response.user.password == password) {
                                    UserSession.userId = response.user.id_user
                                    UserSession.userName = response.user.name
                                    onLoginSuccess()
                                } else {
                                    errorMessage = "Sai mật khẩu!"
                                }
                            } else {
                                errorMessage = "Không tìm thấy tài khoản!"
                            }
                        } catch (e: Exception) {
                            loading = false
                            errorMessage = "Không kết nối được server!"
                        }
                    }
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Login")
                }
            }
            if (errorMessage != null) {
                Spacer(Modifier.height(8.dp))
                Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onSignUp, modifier = Modifier.fillMaxWidth()) {
                Text("Sign Up")
            }
        }
    }
}