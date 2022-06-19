package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.FinanceDetail
import kotlinx.coroutines.flow.Flow

interface FinanceDetailUseCase {

    fun getAllFinanceDetailByType(type: List<String>): Flow<List<FinanceDetail>>

    fun insertFinanceDetail(financeDetail: FinanceDetail)

    fun updateFinanceDetail(
        financeDetail: FinanceDetail,
        newType: String,
        newName: String,
        newExpense: Int
    )

    fun updateFinanceDetailType(
        oldType: String,
        newType: String
    )

    fun deleteFinanceDetail(financeDetail: FinanceDetail)
}