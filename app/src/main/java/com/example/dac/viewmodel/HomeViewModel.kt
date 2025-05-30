// viewmodel/HomeViewModel.kt
package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.data.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: VocabularyRepository) : ViewModel() {
    private val _learnList = MutableStateFlow<List<String>>(emptyList())
    val learnList: StateFlow<List<String>> = _learnList

    fun loadNewWords(userId: Int) {
        viewModelScope.launch {
            try {
                val list = repository.fetchNewWords(userId)
                _learnList.value = list
            } catch (e: Exception) {
                _learnList.value = emptyList()
            }
        }
    }

    fun loadReviewWords(userId: Int) {
        viewModelScope.launch {
            try {
                val list = repository.fetchReviewWords(userId)
                _learnList.value = list
            } catch (e: Exception) {
                _learnList.value = emptyList()
            }
        }
    }
}
