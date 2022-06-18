package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Finance
import com.roviery.core.domain.repository.IFinanceRepository
import kotlinx.coroutines.flow.Flow

class FinanceInteractor(private val financeRepository: IFinanceRepository) : FinanceUseCase {
    override fun getAllFinance(): Flow<List<Finance>> = financeRepository.getAllFinance()

    override fun getAllFinanceType(): Flow<List<String>> = financeRepository.getAllFinanceType()

    override fun getFinanceByType(type: String): Finance = financeRepository.getFinanceByType(type)

    override fun insertFinance(finance: Finance) = financeRepository.insertFinance(finance)

    override fun updateFinance(finance: Finance, newType: String, newFundAllocation: Int, newUsedFund: Int, newRemainingFund: Int) =
        financeRepository.updateFinance(finance, newType, newFundAllocation, newUsedFund, newRemainingFund)

    override fun deleteFinance(finance: Finance) = financeRepository.deleteFinance(finance)
}