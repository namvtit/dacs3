package com.example.dac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dac.data.VocabularyL3Repository
import com.example.dac.model.VocabularyL3
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VocabularyL3ViewModel(private val repo: VocabularyL3Repository): ViewModel() {
    private val _words = MutableStateFlow<List<VocabularyL3>>(emptyList())
    val words: StateFlow<List<VocabularyL3>> = _words

    fun loadWords(idUser: Int) = viewModelScope.launch {
        _words.value = repo.getAll(idUser)
    }
    fun addWord(idUser: Int, idVocabulary: Int, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        onResult(repo.add(idUser, idVocabulary).success)
    }
    fun deleteWord(idUser: Int, idVocabulary: Int, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        onResult(repo.delete(idUser, idVocabulary).success)
    }
}
