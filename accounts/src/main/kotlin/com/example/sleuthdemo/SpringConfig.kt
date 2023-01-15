package com.example.sleuthdemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class SpringConfig {
    @Bean
    fun cardRestClient(objectMapper: ObjectMapper): CardRestClient = Retrofit
        .Builder()
        .baseUrl("http://localhost:8082")
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()
        .create(CardRestClient::class.java)

    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
    }
}
