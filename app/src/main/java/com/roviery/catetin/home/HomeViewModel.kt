package com.roviery.catetin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.model.Finance
import com.roviery.core.domain.model.Quicknotes
import com.roviery.core.domain.usecase.DeadlineUseCase
import com.roviery.core.domain.usecase.FinanceDetailUseCase
import com.roviery.core.domain.usecase.FinanceUseCase
import com.roviery.core.domain.usecase.QuicknotesUseCase

class HomeViewModel(
    private val deadlineUseCase: DeadlineUseCase,
    private val quicknotesUseCase: QuicknotesUseCase,
    private val financeUseCase: FinanceUseCase,
    private val financeDetailUseCase: FinanceDetailUseCase,
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

    fun updateFinanceDetailType(
        oldType: String,
        newType: String
    ) = financeDetailUseCase.updateFinanceDetailType(oldType, newType)

    fun deleteFinance(finance: Finance) = financeUseCase.deleteFinance(finance)


}