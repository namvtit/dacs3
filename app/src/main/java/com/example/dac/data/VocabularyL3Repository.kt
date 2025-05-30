package com.example.dac.data

import com.example.dac.model.VocabularyL3
import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class VocabularyL3Repository(private val api: ApiService) {
    suspend fun getAll(idUser: Int): List<VocabularyL3> = api.getVocabularyL3ByUser(idUser)
    suspend fun add(idUser: Int, idVocabulary: Int): ApiResponse =
        api.addVocabularyL3(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
    suspend fun delete(idUser: Int, idVocabulary: Int): ApiResponse =
        api.deleteVocabularyL3(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
}
