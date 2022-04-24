package com.roviery.core.data.source.repository

import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.domain.model.FinanceDetail
import com.roviery.core.domain.repository.IFinanceDetailRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperFinanceDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FinanceDetailRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFinanceDetailRepository {

    override fun getAllFinanceDetail(): Flow<List<FinanceDetail>> =
        localDataSource.getAllFinanceDetail().map {
            DataMapperFinanceDetail.mapEntitiesToDomain(it)
        }


    override fun insertFinanceDetail(financeDetail: FinanceDetail) {
        val financeDetailEntity = DataMapperFinanceDetail.mapDomainToEntity(financeDetail)
        appExecutors.diskIO()
            .execute { localDataSource.insertFinanceDetail(financeDetailEntity) }
    }

    override fun updateFinanceDetail(
        financeDetail: FinanceDetail,
        newType: String,
        newName: String,
        newExpense: Int
    ) {
        val financeDetailEntity = DataMapperFinanceDetail.mapDomainToEntity(financeDetail)
        appExecutors.diskIO()
            .execute {
                localDataSource.updateFinanceDetail(
                    financeDetailEntity,
                    newType,
                    newName,
                    newExpense
                )
            }
    }

    override fun deleteFinanceDetail(financeDetail: FinanceDetail) {
        val financeDetailEntity = DataMapperFinanceDetail.mapDomainToEntity(financeDetail)
        appExecutors.diskIO()
            .execute { localDataSource.deleteFinanceDetail(financeDetailEntity) }
    }

}