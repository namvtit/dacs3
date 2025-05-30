package com.example.dac.data

import com.example.dac.network.ApiService

class VocabularyRepository(private val api: ApiService) {
    suspend fun getAllVocabulary(): List<Vocabulary> = api.getAllVocabulary()
    suspend fun fetchNewWords(userId: Int) = api.getNewWords(userId)
    suspend fun fetchReviewWords(userId: Int) = api.getReviewWords(userId)
}
