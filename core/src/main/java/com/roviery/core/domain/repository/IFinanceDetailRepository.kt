package com.roviery.core.domain.repository

import com.roviery.core.domain.model.FinanceDetail
import kotlinx.coroutines.flow.Flow

interface IFinanceDetailRepository {

    fun getAllFinanceDetail(): Flow<List<FinanceDetail>>

    fun insertFinanceDetail(financeDetail: FinanceDetail)

    fun updateFinanceDetail(
        financeDetail: FinanceDetail,
        newType: String,
        newName: String,
        newExpense: Int
    )

    fun deleteFinanceDetail(financeDetail: FinanceDetail)
}