package com.example.sleuthdemo

data class AccountResponseDto(
    val id: String,
    val name: String,
    val cards: List<CardDto>? = null,
)
