package com.roviery.catetin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.model.Finance
import com.roviery.core.domain.model.FinanceDetail
import com.roviery.core.domain.model.Quicknotes
import com.roviery.core.domain.usecase.DeadlineUseCase
import com.roviery.core.domain.usecase.FinanceDetailUseCase
import com.roviery.core.domain.usecase.FinanceUseCase
import com.roviery.core.domain.usecase.QuicknotesUseCase

class HomeViewModel(
    private val deadlineUseCase: DeadlineUseCase,
    private val quicknotesUseCase: QuicknotesUseCase,
    private val financeUseCase: FinanceUseCase,
    private val financeDetailUseCase: FinanceDetailUseCase
) : ViewModel() {

    // Deadline
    val listDeadline = deadlineUseCase.getAllDeadline().asLiveData()
    fun insertDeadline(deadline: Deadline) = deadlineUseCase.insertDeadline(deadline)
    fun updateDeadline(deadline: Deadline, newDate: String, newDeadlineNotes: String) =
        deadlineUseCase.updateDeadline(deadline, newDate, newDeadlineNotes)

    fun deleteDeadline(deadline: Deadline) = deadlineUseCase.deleteDeadline(deadline)

    // Quicknotes
    val listQuicknotes = quicknotesUseCase.getAllQuicknotes().asLiveData()
    fun insertQuicknotes(quicknotes: Quicknotes) = quicknotesUseCase.insertQuicknotes(quicknotes)
    fun updateQuicknotes(quicknotes: Quicknotes, newText: String) =
        quicknotesUseCase.updateQuicknotes(quicknotes, newText)

    fun deleteQuicknotes(quicknotes: Quicknotes) = quicknotesUseCase.deleteQuicknotes(quicknotes)

    // Finance
    val listFinance = financeUseCase.getAllFinance().asLiveData()
    fun insertFinance(finance: Finance) = financeUseCase.insertFinance(finance)
    fun updateFinance(finance: Finance, newType: String, newFundAllocation: Int) =
        financeUseCase.updateFinance(finance, newType, newFundAllocation)

    fun deleteFinance(finance: Finance) = financeUseCase.deleteFinance(finance)

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

    fun deleteFinanceDetail(financeDetail: FinanceDetail) = financeDetailUseCase.deleteFinanceDetail(financeDetail)

}