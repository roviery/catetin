package com.roviery.core.data.source.repository

import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.domain.model.Quicknotes
import com.roviery.core.domain.repository.IQuicknotesRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperQuicknotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuicknotesRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IQuicknotesRepository {

    override fun getAllQuicknotes(): Flow<List<Quicknotes>> =
        localDataSource.getAllQuicknotes().map {
            DataMapperQuicknotes.mapEntitiesToDomain(it)
        }

    override fun insertQuicknotes(quicknotes: Quicknotes) {
        val quicknotesEntity = DataMapperQuicknotes.mapDomainToEntity(quicknotes)
        appExecutors.diskIO()
            .execute { localDataSource.insertQuicknotes(quicknotesEntity) }
    }

    override fun updateQuicknotes(quicknotes: Quicknotes, newText: String) {
        val quicknotesEntity = DataMapperQuicknotes.mapDomainToEntity(quicknotes)
        appExecutors.diskIO()
            .execute { localDataSource.updateQuicknotes(quicknotesEntity, newText) }
    }

    override fun deleteQuicknotes(quicknotes: Quicknotes) {
        val quicknotesEntity = DataMapperQuicknotes.mapDomainToEntity(quicknotes)
        appExecutors.diskIO()
            .execute { localDataSource.deleteQuicknotes(quicknotesEntity) }
    }


}