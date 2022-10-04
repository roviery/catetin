package com.roviery.core.domain.repository

import com.roviery.core.domain.model.Finance
import kotlinx.coroutines.flow.Flow

interface IFinanceRepository {

    fun getAllFinance(): Flow<List<Finance>>

    fun getFinanceBudget(): Flow<Int>

    fun getAllFinanceType(): Flow<List<String>>

    fun getFinanceByType(type: String): Finance

    fun insertFinance(finance: Finance)

    fun updateFinance(
        finance: Finance,
        newType: String,
        newFundAllocation: Int,
        newUsedFund: Int,
        newRemainingFund: Int
    )

    fun deleteFinance(finance: Finance)
}