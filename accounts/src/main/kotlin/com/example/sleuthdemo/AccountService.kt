package com.example.sleuthdemo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val cardRestClient: CardRestClient,
) {
    suspend fun findAccountCards(accountId: String): AccountResponseDto = withContext(Dispatchers.IO) {
        logger.info { "account service start" }
        val cards = cardRestClient.findCards()
        val account = AccountResponseDto(
            id = accountId,
            name = "bla",
            cards = cards.items.toList()
        )
        logger.info { "account service end" }
        return@withContext account
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
