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

data class QuizUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val vocabularies: List<Vocabulary> = emptyList(),
    val currentIndex: Int = 0,
    val userInput: String = "",
    val showFlashCard: Boolean = false,
    val isBack: Boolean = false,
    val finished: Boolean = false,
    val score: Int = 0
)

class QuizViewModel(
    private val repository: AppRepository = AppRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState

    init {
        fetchVocabularies()
    }

    fun fetchVocabularies() {
        _uiState.value = QuizUiState(loading = true)
        viewModelScope.launch {
            try {
                val res = repository.getVocabularies(UserSession.userId)
                if (res.success && res.data != null) {
                    _uiState.value = QuizUiState(
                        loading = false,
                        vocabularies = res.data.take(10)
                    )
                } else {
                    _uiState.value = QuizUiState(
                        loading = false,
                        error = "Không lấy được dữ liệu"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = QuizUiState(
                    loading = false,
                    error = "Lỗi mạng"
                )
            }
        }
    }

    fun onUserInputChange(newInput: String) {
        _uiState.value = _uiState.value.copy(userInput = newInput)
    }

    fun checkAnswer() {
        val state = _uiState.value
        if (state.vocabularies.isEmpty() || state.currentIndex >= state.vocabularies.size) return
        val vocab = state.vocabularies[state.currentIndex]
        val correct = vocab.word.trim().equals(state.userInput.trim(), ignoreCase = true)
        if (correct) {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            viewModelScope.launch {
                // Luôn luôn thêm mới learned_word với level = 1 khi trả lời đúng
                repository.addLearnedWord(
                    idUser = UserSession.userId,
                    idVocabulary = vocab.id_vocabulary,
                    level = 1,
                    date = date
                )
            }
            nextWord(increaseScore = true)
        } else {
            _uiState.value = state.copy(showFlashCard = true, isBack = false)
        }
    }

    fun flipCard() {
        _uiState.value = _uiState.value.copy(isBack = !_uiState.value.isBack)
    }

    fun nextWord(increaseScore: Boolean = false) {
        val state = _uiState.value
        val nextIdx = state.currentIndex + 1
        val newScore = if (increaseScore) state.score + 1 else state.score
        val finishedNow = nextIdx >= state.vocabularies.size
        _uiState.value = state.copy(
            currentIndex = nextIdx,
            userInput = "",
            showFlashCard = false,
            isBack = false,
            finished = finishedNow,
            score = newScore
        )
        if (finishedNow) {
            saveQuizResult(newScore)
        }
    }

    private fun saveQuizResult(score: Int) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            repository.addTest(
                idUser = UserSession.userId,
                score = score,
                date = date
            )
        }
    }
}