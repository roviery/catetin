package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.FinanceDetail
import com.roviery.core.domain.repository.IFinanceDetailRepository
import kotlinx.coroutines.flow.Flow

class FinanceDetailInteractor(private val financeDetailRepository: IFinanceDetailRepository) :
    FinanceDetailUseCase {

    override fun getAllFinanceDetailByType(type: List<String>): Flow<List<FinanceDetail>> =
        financeDetailRepository.getAllFinanceDetailByType(type)

    override fun insertFinanceDetail(financeDetail: FinanceDetail) =
        financeDetailRepository.insertFinanceDetail(financeDetail)

    override fun updateFinanceDetail(
        financeDetail: FinanceDetail,
        newType: String,
        newName: String,
        newExpense: Int
    ) = financeDetailRepository.updateFinanceDetail(financeDetail, newType, newName, newExpense)

    override fun deleteFinanceDetail(financeDetail: FinanceDetail) =
        financeDetailRepository.deleteFinanceDetail(financeDetail)

}