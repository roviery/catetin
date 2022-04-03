package com.roviery.core.data.source.repository

import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.repository.IDeadlineRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperDeadline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeadlineRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDeadlineRepository {

    override fun getAllDeadline(): Flow<List<Deadline>> =
        localDataSource.getAllDeadline().map {
            DataMapperDeadline.mapEntitiesToDomain(it)
        }

    override fun insertDeadline(deadline: Deadline) {
        val deadlineEntity = DataMapperDeadline.mapDomainToEntity(deadline)
        appExecutors.diskIO()
            .execute { localDataSource.insertDeadline(deadlineEntity) }
    }

    override fun updateDeadline(deadline: Deadline, newDate: String, newDeadlineNotes: String) {
        val deadlineEntity = DataMapperDeadline.mapDomainToEntity(deadline)
        appExecutors.diskIO()
            .execute { localDataSource.updateDeadline(deadlineEntity, newDate, newDeadlineNotes) }
    }

    override fun deleteDeadline(deadline: Deadline) {
        val deadlineEntity = DataMapperDeadline.mapDomainToEntity(deadline)
        appExecutors.diskIO()
            .execute { localDataSource.deleteDeadline(deadlineEntity) }
    }

}