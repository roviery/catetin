package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Finance
import kotlinx.coroutines.flow.Flow

interface FinanceUseCase {

    fun getAllFinance(): Flow<List<Finance>>

    fun insertFinance(finance: Finance)

    fun updateFinance(finance: Finance, newType: String, newFundAllocation: Int)

    fun deleteFinance(finance: Finance)

}