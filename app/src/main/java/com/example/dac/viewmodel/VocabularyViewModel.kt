package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.data.VocabularyRepository
import com.example.dac.model.Vocabulary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VocabularyViewModel(private val repo: VocabularyRepository): ViewModel() {
    private val _vocab = MutableStateFlow<List<Vocabulary>>(emptyList())
    val vocab: StateFlow<List<Vocabulary>> = _vocab

    fun loadVocabulary() = viewModelScope.launch {
        _vocab.value = repo.getAllVocabulary()
    }
}
