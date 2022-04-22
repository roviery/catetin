package com.roviery.core.data.source.local

import com.roviery.core.data.source.local.entity.DeadlineEntity
import com.roviery.core.data.source.local.entity.QuicknotesEntity
import com.roviery.core.data.source.local.room.CatetinDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val catetinDao: CatetinDao) {

    // Deadline

    fun getAllDeadline(): Flow<List<DeadlineEntity>> = catetinDao.getAllDeadline()

    fun insertDeadline(deadline: DeadlineEntity) = catetinDao.insertDeadline(deadline)

    fun updateDeadline(deadline: DeadlineEntity, newDate: String, newDeadlineNotes: String){
        deadline.deadlineDate = newDate
        deadline.deadlineNotes = newDeadlineNotes
        catetinDao.updateDeadline(deadline)
    }

    fun deleteDeadline(deadline: DeadlineEntity) = catetinDao.deleteDeadline(deadline)

    // Quicknotes

    fun getAllQuicknotes(): Flow<List<QuicknotesEntity>> = catetinDao.getAllQuicknotes()

    fun insertQuicknotes(quicknotes: QuicknotesEntity) = catetinDao.insertQuicknotes(quicknotes)

    fun updateQuicknotes(quicknotes: QuicknotesEntity, newText: String){
        quicknotes.quicknotesText = newText
        catetinDao.updateQuicknotes(quicknotes)
    }

}