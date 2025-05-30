package com.example.dac.model

data class UserResponse(
    val success: Boolean,
    val user: User? = null,
    val error: String? = null
)