package com.example.dac.data

import com.example.dac.model.ApiResponse
import com.example.dac.network.ApiService

class UserRepository(private val api: ApiService) {
    suspend fun findUser(userName: String): User = api.findUser(userName)
    suspend fun addUser(name: String, userName: String, password: String): ApiResponse =
        api.addUser(mapOf("name" to name, "user_name" to userName, "password" to password))
}
