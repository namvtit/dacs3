package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.model.LearnedWord
import com.example.dac.model.UserSession
import com.example.dac.model.Vocabulary
import com.example.dac.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PracticeUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val pairs: List<Pair<LearnedWord, Vocabulary>> = emptyList(),
    val currentIndex: Int = 0,
    val userInput: String = "",
    val showResult: Boolean = false,
    val isCorrect: Boolean = false,
    val finished: Boolean = false
)

class PracticeViewModel(
    private val repository: AppRepository = AppRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(PracticeUiState())
    val uiState: StateFlow<PracticeUiState> = _uiState

    init {
        loadPracticeWords()
    }

    fun loadPracticeWords() {
        _uiState.value = PracticeUiState(loading = true)
        viewModelScope.launch {
            try {
                val learnedRes = repository.getLearnedWordsUnder5(UserSession.userId)
                val vocabRes = repository.getVocabularies(UserSession.userId)
                if (learnedRes.success && learnedRes.data != null &&
                    vocabRes.success && vocabRes.data != null
                ) {
                    val vocabMap = vocabRes.data.associateBy { it.id_vocabulary }
                    val pairs = learnedRes.data.mapNotNull { lw ->
                        vocabMap[lw.id_vocabulary]?.let { v -> lw to v }
                    }
                    _uiState.value = PracticeUiState(
                        loading = false,
                        pairs = pairs
                    )
                } else {
                    _uiState.value = PracticeUiState(loading = false, error = "Không lấy được dữ liệu")
                }
            } catch (e: Exception) {
                _uiState.value = PracticeUiState(loading = false, error = "Lỗi mạng")
            }
        }
    }

    fun onUserInputChange(newInput: String) {
        _uiState.value = _uiState.value.copy(userInput = newInput)
    }

    fun checkAnswer() {
        val idx = _uiState.value.currentIndex
        val pairs = _uiState.value.pairs
        if (idx >= pairs.size) return

        val correctWord = pairs[idx].second.word.trim()
        val userInput = _uiState.value.userInput.trim()
        val isCorrect = correctWord.equals(userInput, ignoreCase = true)

        if (isCorrect) {
            val lw = pairs[idx].first
            viewModelScope.launch {
                repository.updateLearnedLevel(UserSession.userId, lw.id_vocabulary)
            }
        }
        _uiState.value = _uiState.value.copy(
            showResult = true,
            isCorrect = isCorrect
        )
    }

    fun nextWord() {
        val nextIdx = _uiState.value.currentIndex + 1
        _uiState.value = _uiState.value.copy(
            currentIndex = nextIdx,
            userInput = "",
            showResult = false,
            finished = nextIdx >= _uiState.value.pairs.size
        )
    }
}