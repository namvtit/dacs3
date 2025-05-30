package com.example.dac.data

import com.example.dac.model.VocabularyL4
import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class VocabularyL4Repository(private val api: ApiService) {
    suspend fun getAll(idUser: Int): List<VocabularyL4> = api.getVocabularyL4ByUser(idUser)
    suspend fun add(idUser: Int, idVocabulary: Int): ApiResponse =
        api.addVocabularyL4(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
    suspend fun delete(idUser: Int, idVocabulary: Int): ApiResponse =
        api.deleteVocabularyL4(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
}
