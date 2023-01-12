package com.example.sleuthdemo

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.sleuth.Tracer
import org.springframework.cloud.sleuth.instrument.kotlin.asContextElement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val tracer: Tracer
) {

    @GetMapping("/hello")
    suspend fun hello1(): String = coroutineScope {
        logger.info { "start" }
        val asContextElement = tracer.asContextElement()
        launch(asContextElement) {
            delay(300)
            logger.info { "first" }
        }
        launch(asContextElement) {
            delay(300)
            logger.info { "second" }
            launch {
                delay(300)
                logger.info { "third" }
            }
        }
        logger.info { "end" }

        return@coroutineScope "ok"
    }

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }
}
