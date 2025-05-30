package com.example.dac.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.model.UserSession
import com.example.dac.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val levelCounts: Map<String, Int> = emptyMap(),
    val userName: String = ""
)

class UserViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState

    fun loadUserData() {
        val userId = UserSession.userId
        val userName = UserSession.userName
        _uiState.value = UserUiState(loading = true, userName = userName)
        viewModelScope.launch {
            try {
                val res = RetrofitInstance.api.countLevel(idUser = userId)
                if (res.success && res.data != null) {
                    _uiState.value = UserUiState(
                        loading = false,
                        levelCounts = res.data,
                        userName = userName
                    )
                } else {
                    _uiState.value = UserUiState(
                        loading = false,
                        error = "Không lấy được dữ liệu",
                        userName = userName
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UserUiState(
                    loading = false,
                    error = "Lỗi mạng",
                    userName = userName
                )
            }
        }
    }
}