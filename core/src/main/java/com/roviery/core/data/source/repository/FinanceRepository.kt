package com.roviery.core.data.source.repository

import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.domain.model.Finance
import com.roviery.core.domain.repository.IFinanceRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperFinance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FinanceRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFinanceRepository {

    override fun getAllFinance(): Flow<List<Finance>> =
        localDataSource.getAllFinance().map {
            DataMapperFinance.mapEntitiesToDomain(it)
        }

    override fun getFinanceAllocation(): Flow<Int> = localDataSource.getFinanceAllocation()

    override fun getFinanceBudget(): Flow<Int> =
        localDataSource.getFinanceBudget()

    override fun getAllFinanceType(): Flow<List<String>> =
        localDataSource.getAllFinanceType()

    override fun getFinanceByType(type: String): Finance {
        val financeEntity = localDataSource.getFinanceByType(type)
        return DataMapperFinance.entityToDomain(financeEntity)
    }

    override fun insertFinance(finance: Finance) {
        val financeEntity = DataMapperFinance.mapDomainToEntity(finance)
        appExecutors.diskIO()
            .execute { localDataSource.insertFinance(financeEntity) }
    }

    override fun updateFinance(
        finance: Finance,
        newType: String,
        newFundAllocation: Int,
        newUsedFund: Int,
        newRemainingFund: Int
    ) {
        val financeEntity = DataMapperFinance.mapDomainToEntity(finance)
        appExecutors.diskIO()
            .execute {
                localDataSource.updateFinance(
                    financeEntity,
                    newType,
                    newFundAllocation,
                    newUsedFund,
                    newRemainingFund,
                )
            }
    }

    override fun deleteFinance(finance: Finance) {
        val financeEntity = DataMapperFinance.mapDomainToEntity(finance)
        appExecutors.diskIO()
            .execute { localDataSource.deleteFinance(financeEntity) }
    }

}