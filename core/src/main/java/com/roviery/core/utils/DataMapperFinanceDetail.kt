package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.FinanceDetailEntity
import com.roviery.core.domain.model.FinanceDetail

object DataMapperFinanceDetail {
    fun mapDomainToEntity(input: FinanceDetail) =
        FinanceDetailEntity(
            financeDetailId = input.financeDetailId,
            financeDetailType = input.financeDetailType,
            financeDetailName = input.financeDetailName,
            financeDetailExpense = input.financeDetailExpense
        )

    fun mapEntitiesToDomain(input: List<FinanceDetailEntity>): List<FinanceDetail> =
        input.map {
            FinanceDetail(
                financeDetailId = it.financeDetailId,
                financeDetailType = it.financeDetailType,
                financeDetailName = it.financeDetailName,
                financeDetailExpense = it.financeDetailExpense
            )
        }
}