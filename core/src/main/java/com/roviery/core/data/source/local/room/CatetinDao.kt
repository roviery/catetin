package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatetinDao {

    // Deadline

    @Query("SELECT * FROM Deadline ORDER BY deadlineDate")
    fun getAllDeadline(): Flow<List<DeadlineEntity>>

    @Insert
    fun insertDeadline(deadline: DeadlineEntity)

    @Update
    fun updateDeadline(deadline: DeadlineEntity)

    @Delete
    fun deleteDeadline(deadline: DeadlineEntity)

    // Quicknotes

    @Query("SELECT * FROM Quicknotes ORDER BY quicknotesId")
    fun getAllQuicknotes(): Flow<List<QuicknotesEntity>>

    @Insert
    fun insertQuicknotes(quicknotes: QuicknotesEntity)

    @Update
    fun updateQuicknotes(quicknotes: QuicknotesEntity)

    @Delete
    fun deleteQuicknotes(quicknotes: QuicknotesEntity)

    // Finance

    @Query("SELECT * FROM Finance")
    fun getAllFinance(): Flow<List<FinanceEntity>>

    @Query("SELECT FType FROM Finance")
    fun getAllFinanceType(): Flow<List<String>>

    @Query("SELECT * FROM Finance WHERE FType = :type")
    fun getFinanceWithType(type: String): FinanceEntity

    @Insert
    fun insertFinance(finance: FinanceEntity)

    @Update
    fun updateFinance(finance: FinanceEntity)

    @Delete
    fun deleteFinance(finance: FinanceEntity)

    // Finance Detail

    @Query("SELECT * FROM FinanceDetail ORDER BY FDType")
    fun getAllFinanceDetail(): Flow<List<FinanceDetailEntity>>

    @Insert
    fun insertFinanceDetail(financeDetail: FinanceDetailEntity)

    @Update
    fun updateFinanceDetail(financeDetail: FinanceDetailEntity)

    @Delete
    fun deleteFinanceDetail(financeDetail: FinanceDetailEntity)

    @Query("DELETE FROM FinanceDetail WHERE FDType = :type")
    fun deleteAllFinanceDetailWithType(type: String)

    // Todo

    @Query("SELECT * FROM Todo WHERE todoStatus = :status")
    fun getAllTodo(status: String): Flow<List<TodoEntity>>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)

}