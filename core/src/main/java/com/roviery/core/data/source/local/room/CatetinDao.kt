package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.DeadlineEntity
import com.roviery.core.data.source.local.entity.QuicknotesEntity
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

}