package com.example.dac.model

data class ApiResponse(
    val success: Boolean,
    val id: Int? = null,
    val error: String? = null
)
