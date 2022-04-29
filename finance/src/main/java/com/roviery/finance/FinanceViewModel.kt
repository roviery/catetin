package com.roviery.finance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.FinanceDetail
import com.roviery.core.domain.usecase.FinanceDetailUseCase

class FinanceViewModel(private val financeDetailUseCase: FinanceDetailUseCase) : ViewModel() {

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