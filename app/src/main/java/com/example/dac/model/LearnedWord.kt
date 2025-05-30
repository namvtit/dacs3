package com.example.dac.model

data class LearnedWord(
    val id_lw: Int,
    val id_user: Int,
    val id_vocabulary: Int,
    val level: Int,
    val date: String
)