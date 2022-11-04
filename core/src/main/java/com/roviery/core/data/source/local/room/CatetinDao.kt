package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatetinDao {

    // Deadline

    @Query("SELECT * FROM Deadline ORDER BY deadlineDate ASC")
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

    @Query("SELECT SUM(FFundAllocation) FROM Finance")
    fun getFinanceAllocation(): Flow<Int>

    @Query("SELECT SUM(FRemainingFund) FROM Finance")
    fun getFinanceBudget(): Flow<Int>

    @Query("SELECT FType FROM Finance ORDER BY FType ASC")
    fun getAllFinanceType(): Flow<List<String>>

    @Query("SELECT * FROM Finance WHERE FType = :type")
    fun getFinanceByType(type: String): FinanceEntity

    @Insert
    fun insertFinance(finance: FinanceEntity)

    @Update
    fun updateFinance(finance: FinanceEntity)

    @Delete
    fun deleteFinance(finance: FinanceEntity)

    // Finance Detail

    @Query("SELECT * FROM FinanceDetail ORDER BY FDDate DESC")
    fun getAllFinanceDetail(): Flow<List<FinanceDetailEntity>>

    @Query("SELECT * FROM FinanceDetail WHERE FDType IN(:type) ORDER BY FDDate DESC")
    fun getAllFinanceDetailByType(type: List<String>): Flow<List<FinanceDetailEntity>>

    @Insert
    fun insertFinanceDetail(financeDetail: FinanceDetailEntity)

    @Update
    fun updateFinanceDetail(financeDetail: FinanceDetailEntity)

    @Query("UPDATE FinanceDetail SET FDType = :newType WHERE FDType = :oldType")
    fun updateFinanceDetailType(oldType: String, newType: String)

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