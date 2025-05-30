package com.example.dac.data

import com.example.dac.model.VocabularyL2
import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class VocabularyL2Repository(private val api: ApiService) {
    suspend fun getAll(idUser: Int): List<VocabularyL2> = api.getVocabularyL2ByUser(idUser)
    suspend fun add(idUser: Int, idVocabulary: Int): ApiResponse =
        api.addVocabularyL2(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
    suspend fun delete(idUser: Int, idVocabulary: Int): ApiResponse =
        api.deleteVocabularyL2(mapOf("id_user" to idUser, "id_vocabulary" to idVocabulary))
}
