package com.roviery.finance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.Finance
import com.roviery.core.domain.model.FinanceDetail
import com.roviery.core.domain.usecase.FinanceDetailUseCase
import com.roviery.core.domain.usecase.FinanceUseCase

class FinanceViewModel(
    private val financeUseCase: FinanceUseCase,
    private val financeDetailUseCase: FinanceDetailUseCase
) : ViewModel() {

    val listFinanceType = financeUseCase.getAllFinanceType().asLiveData()

    fun getFinanceWithType(type: String) = financeUseCase.getFinanceWithType(type)

    fun updateFinance(
        finance: Finance,
        newType: String,
        newFundAllocation: Int,
        newUsedFund: Int,
        newRemainingFund: Int
    ) =
        financeUseCase.updateFinance(
            finance,
            newType,
            newFundAllocation,
            newUsedFund,
            newRemainingFund,
        )


    // Finance Detail
    val listFinanceDetail = financeDetailUseCase.getAllFinanceDetail().asLiveData()
    fun insertFinanceDetail(financeDetail: FinanceDetail) =
        financeDetailUseCase.insertFinanceDetail(financeDetail)

    fun updateFinanceDetail(
        financeDetail: FinanceDetail,
        newType: String,
        newName: String,
        newExpense: Int
    ) = financeDetailUseCase.updateFinanceDetail(financeDetail, newType, newName, newExpense)

    fun deleteFinanceDetail(financeDetail: FinanceDetail) =
        financeDetailUseCase.deleteFinanceDetail(financeDetail)

}