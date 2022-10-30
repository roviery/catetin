package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Finance
import kotlinx.coroutines.flow.Flow

interface FinanceUseCase {

    fun getAllFinance(): Flow<List<Finance>>

    fun getFinanceAllocation(): Flow<Int>

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