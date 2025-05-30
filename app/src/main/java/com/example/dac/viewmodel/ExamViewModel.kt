package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.model.Test
import com.example.dac.model.UserSession
import com.example.dac.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ExamUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val tests: List<Test> = emptyList()
)

class ExamViewModel(
    private val repository: AppRepository = AppRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow(ExamUiState())
    val uiState: StateFlow<ExamUiState> = _uiState

    init {
        fetchTests()
    }

    fun fetchTests() {
        _uiState.value = ExamUiState(loading = true)
        viewModelScope.launch {
            try {
                val res = repository.getAllTests(UserSession.userId)
                if (res.success && res.data != null) {
                    _uiState.value = ExamUiState(
                        loading = false,
                        tests = res.data
                    )
                } else {
                    _uiState.value = ExamUiState(
                        loading = false,
                        error = "Không lấy được dữ liệu bài kiểm tra"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = ExamUiState(
                    loading = false,
                    error = "Lỗi mạng"
                )
            }
        }
    }
}