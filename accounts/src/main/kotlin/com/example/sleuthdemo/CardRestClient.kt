package com.example.sleuthdemo

import retrofit2.http.GET

interface CardRestClient {
    @GET("cards")
    suspend fun findCards(): CardResponseDto
}
