package com.example.sleuthdemo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.sleuth.Tracer
import org.springframework.cloud.sleuth.instrument.kotlin.asContextElement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CardController(
    private val cardService: CardService,
    private val tracer: Tracer,
) {

    @GetMapping("/cards")
    suspend fun findCards(): CardResponseDto = coroutineScope {
        logger.info { "card controller start" }
        // coroutines need context element to show tracing fields in logs
        val asContextElement = tracer.asContextElement()
        val cardDeferred = async(asContextElement) {
            cardService.findCards()
        }
        val cardResponseDto = cardDeferred.await()
        logger.info { "card controller end" }

        return@coroutineScope cardResponseDto
    }

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }
}
