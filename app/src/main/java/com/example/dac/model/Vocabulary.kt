package com.example.dac.model

data class Vocabulary(
    val id_vocabulary: Int,
    val word: String,
    val ipa: String?,
    val context_english: String?,
    val context_vietnamese: String?,
    val meaning: String?,
    val level: String?
)