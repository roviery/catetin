package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.DeadlineEntity
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

}