package com.example.sleuthdemo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.sleuth.Tracer
import org.springframework.cloud.sleuth.instrument.kotlin.asContextElement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountService: AccountService,
    private val tracer: Tracer,
) {
    @GetMapping("/account/{accountId}")
    suspend fun findAccounts(@PathVariable accountId: String): AccountResponseDto = coroutineScope {
        logger.info { "account controller start" }
        // coroutines need context element to show tracing fields in logs
        val asContextElement = tracer.asContextElement()
        val accountsDeferred = async(asContextElement) {
            accountService.findAccountCards(accountId)
        }
        val accounts = accountsDeferred.await()
        logger.info { "account controller end" }

        return@coroutineScope accounts
    }

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }
}
