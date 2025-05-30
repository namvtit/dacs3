package com.example.dac.data

import com.example.dac.model.Test
import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class TestRepository(private val api: ApiService) {
    suspend fun getTestsOfUser(idUser: Int): List<Test> = api.getTestsOfUser(idUser)
    suspend fun addTest(idUser: Int, score: Int): ApiResponse =
        api.addTest(mapOf("id_user" to idUser, "score" to score))
}
