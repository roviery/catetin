package com.roviery.catetin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.model.Quicknotes
import com.roviery.core.domain.usecase.DeadlineUseCase
import com.roviery.core.domain.usecase.QuicknotesUseCase

class HomeViewModel(
    private val deadlineUseCase: DeadlineUseCase,
    private val quicknotesUseCase: QuicknotesUseCase
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

}