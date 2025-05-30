package com.example.dac.data

import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class VocabularyL1Repository(private val api: ApiService) {
    suspend fun getAll(idUser: Int): List<VocabularyL1> = api.getVocabularyL1ByUser(idUser)
    suspend fun add(idUser: Int, idVocabulary: Int): ApiResponse =
        api.addVocabularyL1(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
    suspend fun delete(idUser: Int, idVocabulary: Int): ApiResponse =
        api.deleteVocabularyL1(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
}
