package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Quicknotes
import com.roviery.core.domain.repository.IQuicknotesRepository

class QuicknotesInteractor(private val quicknotesRepository: IQuicknotesRepository) :
    QuicknotesUseCase {

    override fun getAllQuicknotes() =
        quicknotesRepository.getAllQuicknotes()

    override fun insertQuicknotes(quicknotes: Quicknotes) =
        quicknotesRepository.insertQuicknotes(quicknotes)

    override fun updateQuicknotes(quicknotes: Quicknotes, newText: String) =
        quicknotesRepository.updateQuicknotes(quicknotes, newText)

    override fun deleteQuicknotes(quicknotes: Quicknotes) =
        quicknotesRepository.deleteQuicknotes(quicknotes)
}