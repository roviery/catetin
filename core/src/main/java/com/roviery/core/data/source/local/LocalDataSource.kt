package com.roviery.core.data.source.local

import com.roviery.core.data.source.local.entity.*
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

    fun getFinanceBudget(): Flow<Int> = catetinDao.getFinanceBudget()

    fun getAllFinanceType(): Flow<List<String>> = catetinDao.getAllFinanceType()

    fun getFinanceByType(type: String): FinanceEntity = catetinDao.getFinanceByType(type)

    fun insertFinance(finance: FinanceEntity) = catetinDao.insertFinance(finance)

    fun updateFinance(
        finance: FinanceEntity,
        newType: String,
        newFundAllocation: Int,
        newUsedFund: Int,
        newRemainingFund: Int,
    ) {
        finance.financeType = newType
        finance.financeFundAllocation = newFundAllocation
        finance.financeUsedFund = newUsedFund
        finance.financeRemainingFund = newRemainingFund
        catetinDao.updateFinance(finance)
    }

    fun deleteFinance(finance: FinanceEntity) {
        catetinDao.deleteFinance(finance)
        catetinDao.deleteAllFinanceDetailWithType(finance.financeType)
    }

    // Finance Detail
    fun getAllFinanceDetailByType(type: List<String>): Flow<List<FinanceDetailEntity>> {
        if (type.isEmpty())
            return catetinDao.getAllFinanceDetail()
        return catetinDao.getAllFinanceDetailByType(type)
    }

    fun insertFinanceDetail(financeDetail: FinanceDetailEntity) =
        catetinDao.insertFinanceDetail(financeDetail)

    fun updateFinanceDetail(
        financeDetail: FinanceDetailEntity,
        newType: String,
        newName: String,
        newExpense: Int
    ) {
        financeDetail.financeDetailType = newType
        financeDetail.financeDetailDate = financeDetail.financeDetailDate
        financeDetail.financeDetailName = newName
        financeDetail.financeDetailExpense = newExpense
        catetinDao.updateFinanceDetail(financeDetail)
    }

    fun updateFinanceDetailType(oldType: String, newType: String) =
        catetinDao.updateFinanceDetailType(oldType, newType)

    fun deleteFinanceDetail(financeDetail: FinanceDetailEntity) =
        catetinDao.deleteFinanceDetail(financeDetail)

    // Todo

    fun getAllTodo(status: String): Flow<List<TodoEntity>> = catetinDao.getAllTodo(status)

    fun insertTodo(todo: TodoEntity) = catetinDao.insertTodo(todo)

    fun updateTodo(todo: TodoEntity, newStatus: String, newMessage: String) {
        todo.todoStatus = newStatus
        todo.todoMessage = newMessage
        catetinDao.updateTodo(todo)
    }

    fun deleteTodo(todo: TodoEntity) = catetinDao.deleteTodo(todo)

}