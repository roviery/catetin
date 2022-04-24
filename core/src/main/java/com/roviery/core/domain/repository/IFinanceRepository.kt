package com.roviery.core.domain.repository

import com.roviery.core.domain.model.Finance
import kotlinx.coroutines.flow.Flow

interface IFinanceRepository {

    fun getAllFinance(): Flow<List<Finance>>

    fun insertFinance(finance: Finance)

    fun updateFinance(finance: Finance, newType: String, newFundAllocation: Int)

    fun deleteFinance(finance: Finance)
}