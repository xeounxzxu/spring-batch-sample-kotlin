package com.example.dynamicbean.service

import com.example.dynamicbean.service.AccountFaker.getStockAccounts
import com.example.dynamicbean.service.AccountFaker.getTotalAccounts
import com.example.dynamicbean.service.dto.AccountModel
import com.example.dynamicbean.service.dto.AccountType
import org.springframework.stereotype.Service

@Service
class AccountFactoryService(private val accountFactory: Map<AccountType, AccountService>) {
    fun getList(accountType: AccountType): List<AccountModel> {
        return requireNotNull(accountFactory[accountType]).getList()
    }
}

interface AccountService {
    fun getList(): List<AccountModel>

    fun getAccountType(): AccountType
}

@Service
class StockAccountService : AccountService {
    override fun getList(): List<AccountModel> {
        return getStockAccounts()
    }

    override fun getAccountType(): AccountType {
        return AccountType.STOCK
    }
}

@Service
class TotalAccountService : AccountService {
    override fun getList(): List<AccountModel> {
        return getTotalAccounts()
    }

    override fun getAccountType(): AccountType {
        return AccountType.TOTAL
    }
}

object AccountFaker {
    fun getTotalAccounts(): List<AccountModel> {
        return listOf(
            AccountModel(
                "03000000",
                AccountType.TOTAL,
            ),
        )
    }

    fun getStockAccounts(): List<AccountModel> {
        return listOf(
            AccountModel(
                "03000000",
                AccountType.STOCK,
            ),
        )
    }
}
