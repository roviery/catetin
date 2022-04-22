package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Quicknotes
import kotlinx.coroutines.flow.Flow

interface QuicknotesUseCase {

    fun getAllQuicknotes(): Flow<List<Quicknotes>>

    fun insertQuicknotes(quicknotes: Quicknotes)

    fun updateQuicknotes(quicknotes: Quicknotes, newText: String)

    fun deleteQuicknotes(quicknotes: Quicknotes)
}