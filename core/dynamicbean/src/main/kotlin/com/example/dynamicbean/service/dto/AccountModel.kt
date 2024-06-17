package com.example.dynamicbean.service.dto

data class AccountModel(
    val accountNumber: String,
    val accountType: AccountType,
)

enum class AccountType {
    TOTAL, // 종합 계좌
    STOCK, // 주식 계좌
    SUB, // 서브 계좌
}
