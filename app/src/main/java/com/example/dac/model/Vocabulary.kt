package com.example.dac.model

data class Vocabulary(
    val id_vocabulary: Int,
    val vocabulary: String,
    val ipa: String?,
    val context: String?,
    val level: String?
)
