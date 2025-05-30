package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.model.UserSession
import com.example.dac.model.Vocabulary
import com.example.dac.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class LearnUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val vocabularies: List<Vocabulary> = emptyList(),
    val currentIndex: Int = 0,
    val isBack: Boolean = false
)

class LearnViewModel(
    private val repository: AppRepository = AppRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LearnUiState())
    val uiState: StateFlow<LearnUiState> = _uiState

    init {
        fetchVocabularies()
    }

    fun fetchVocabularies() {
        _uiState.value = LearnUiState(loading = true)
        viewModelScope.launch {
            try {
                val res = repository.getVocabularies(UserSession.userId)
                if (res.success && res.data != null) {
                    _uiState.value = LearnUiState(
                        loading = false,
                        vocabularies = res.data.take(10),
                        currentIndex = 0,
                        isBack = false
                    )
                } else {
                    _uiState.value = LearnUiState(
                        loading = false,
                        error = "Không lấy được dữ liệu"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = LearnUiState(
                    loading = false,
                    error = "Lỗi mạng"
                )
            }
        }
    }

    fun flipCard() {
        _uiState.value = _uiState.value.copy(isBack = !_uiState.value.isBack)
    }

    fun nextVocabulary() {
        val list = _uiState.value.vocabularies
        val idx = _uiState.value.currentIndex
        if (list.isEmpty() || idx >= list.size) return

        // Lưu learned_word cho từ hiện tại
        val vocab = list[idx]
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            repository.addLearnedWord(
                idUser = UserSession.userId,
                idVocabulary = vocab.id_vocabulary,
                level = 1,
                date = date
            )
        }
        // Sang từ tiếp theo, reset mặt trước
        _uiState.value = _uiState.value.copy(
            currentIndex = idx + 1,
            isBack = false
        )
    }
}