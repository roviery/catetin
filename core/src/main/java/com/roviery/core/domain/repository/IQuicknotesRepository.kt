package com.roviery.core.domain.repository

import com.roviery.core.domain.model.Quicknotes
import kotlinx.coroutines.flow.Flow

interface IQuicknotesRepository {

    fun getAllQuicknotes(): Flow<List<Quicknotes>>

    fun insertQuicknotes(quicknotes: Quicknotes)

    fun updateQuicknotes(quicknotes: Quicknotes, newText: String)

    fun deleteQuicknotes(quicknotes: Quicknotes)
}