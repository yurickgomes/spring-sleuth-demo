package com.example.sleuthdemo

import brave.http.HttpTracing
import brave.okhttp3.TracingCallFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class SpringConfig(
    private val httpTracing: HttpTracing,
) {
    @Bean
    fun cardRestClient(
        objectMapper: ObjectMapper,
        okHttpClient: OkHttpClient
    ): CardRestClient = Retrofit
        .Builder()
        .callFactory(TracingCallFactory.create(httpTracing, okHttpClient))
        .baseUrl("http://localhost:8082")
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()
        .create(CardRestClient::class.java)

    @Bean
    fun okHttpClient(): OkHttpClient = OkHttpClient()

    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
    }
}
