package com.example.sleuthdemo

import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CardService {
    suspend fun findCards(): CardResponseDto = withContext(Dispatchers.IO) {
        logger.info { "card service start" }
        delay(300) // mimic a database call or something else
        val mockCardFirst = CardDto(id = UUID.randomUUID().toString(), number = "1234")
        val mockCardSecond = CardDto(id = UUID.randomUUID().toString(), number = "5678")
        logger.info { "card service end" }
        return@withContext CardResponseDto(items = listOf(mockCardFirst, mockCardSecond))
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
