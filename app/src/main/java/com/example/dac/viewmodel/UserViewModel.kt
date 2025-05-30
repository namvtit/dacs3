package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.data.UserRepository
import com.example.dac.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repo: UserRepository): ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            try {
                val user = repo.findUser(userName)
                if (user == null) {
                    _loginState.value = LoginState.UserNotFound
                } else if (user.password != password) {
                    _loginState.value = LoginState.WrongPassword
                } else {
                    _loginState.value = LoginState.Success(user)
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Lỗi server hoặc kết nối: ${e.message}")
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}

// Trạng thái đăng nhập
sealed class LoginState {
    object Idle : LoginState()
    object UserNotFound : LoginState()
    object WrongPassword : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}
