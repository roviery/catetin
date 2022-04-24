package com.roviery.core.data.source.local

import com.roviery.core.data.source.local.entity.DeadlineEntity
import com.roviery.core.data.source.local.entity.FinanceDetailEntity
import com.roviery.core.data.source.local.entity.FinanceEntity
import com.roviery.core.data.source.local.entity.QuicknotesEntity
import com.roviery.core.data.source.local.room.CatetinDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val catetinDao: CatetinDao) {

    // Deadline

    fun getAllDeadline(): Flow<List<DeadlineEntity>> = catetinDao.getAllDeadline()

    fun insertDeadline(deadline: DeadlineEntity) = catetinDao.insertDeadline(deadline)

    fun updateDeadline(deadline: DeadlineEntity, newDate: String, newDeadlineNotes: String) {
        deadline.deadlineDate = newDate
        deadline.deadlineNotes = newDeadlineNotes
        catetinDao.updateDeadline(deadline)
    }

    fun deleteDeadline(deadline: DeadlineEntity) = catetinDao.deleteDeadline(deadline)

    // Quicknotes

    fun getAllQuicknotes(): Flow<List<QuicknotesEntity>> = catetinDao.getAllQuicknotes()

    fun insertQuicknotes(quicknotes: QuicknotesEntity) = catetinDao.insertQuicknotes(quicknotes)

    fun updateQuicknotes(quicknotes: QuicknotesEntity, newText: String) {
        quicknotes.quicknotesText = newText
        catetinDao.updateQuicknotes(quicknotes)
    }

    fun deleteQuicknotes(quicknotes: QuicknotesEntity) = catetinDao.deleteQuicknotes(quicknotes)

    // Finance

    fun getAllFinance(): Flow<List<FinanceEntity>> = catetinDao.getAllFinance()

    fun insertFinance(finance: FinanceEntity) = catetinDao.insertFinance(finance)

    fun updateFinance(finance: FinanceEntity, newType: String, newFundAllocation: Int) {
        finance.financeType = newType
        finance.financeFundAllocation = newFundAllocation
        catetinDao.updateFinance(finance)
    }

    fun deleteFinance(finance: FinanceEntity) = catetinDao.deleteFinance(finance)

    // Finance Detail

    fun getAllFinanceDetail(): Flow<List<FinanceDetailEntity>> = catetinDao.getAllFinanceDetail()

    fun insertFinanceDetail(financeDetail: FinanceDetailEntity) =
        catetinDao.insertFinanceDetail(financeDetail)

    fun updateFinanceDetail(
        financeDetail: FinanceDetailEntity,
        newType: String,
        newName: String,
        newExpense: Int
    ) {
        financeDetail.financeDetailType = newType
        financeDetail.financeDetailName = newName
        financeDetail.financeDetailExpense = newExpense
        catetinDao.updateFinanceDetail(financeDetail)
    }

    fun deleteFinanceDetail(financeDetail: FinanceDetailEntity) =
        catetinDao.deleteFinanceDetail(financeDetail)

}