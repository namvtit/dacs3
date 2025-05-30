package com.example.dac.repository

import com.example.dac.model.*
import com.example.dac.network.RetrofitInstance

class AppRepository {
    private val api = RetrofitInstance.api

    suspend fun addUser(username: String, password: String, name: String) =
        api.addUser(username = username, password = password, name = name)

    suspend fun getUser(username: String) =
        api.getUser(username = username)

    suspend fun getVocabularies(idUser: Int) =
        api.getVocabularies(idUser = idUser)

    suspend fun addLearnedWord(idUser: Int, idVocabulary: Int, level: Int, date: String) =
        api.addLearnedWord(idUser = idUser, idVocabulary = idVocabulary, level = level, date = date)

    suspend fun updateLearnedLevel(idUser: Int, idVocabulary: Int) =
        api.updateLearnedLevel(idUser = idUser, idVocabulary = idVocabulary)

    suspend fun getLearnedWordsUnder5(idUser: Int) =
        api.getLearnedWordsUnder5(idUser = idUser)

    suspend fun countLevel(idUser: Int) =
        api.countLevel(idUser = idUser)

    suspend fun getAllTests(idUser: Int) =
        api.getAllTests(idUser = idUser)

    suspend fun addTest(idUser: Int, score: Int, date: String) =
        api.addTest(idUser = idUser, score = score, date = date)
}