package com.example.dynamicbean.config

import com.example.dynamicbean.service.AccountService
import com.example.dynamicbean.service.dto.AccountType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FactoryConfiguration {
    @Bean
    fun accountFactory(accountService: List<AccountService>): Map<AccountType, AccountService> {
        return accountService.associateBy { it.getAccountType() }
    }
}
