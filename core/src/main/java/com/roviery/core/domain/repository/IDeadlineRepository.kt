package com.roviery.core.domain.repository

import com.roviery.core.domain.model.Deadline
import kotlinx.coroutines.flow.Flow

interface IDeadlineRepository {

    fun getAllDeadline(): Flow<List<Deadline>>

    fun insertDeadline(deadline: Deadline)

    fun updateDeadline(deadline: Deadline, newDate: String, newDeadlineNotes: String)

    fun deleteDeadline(deadline: Deadline)

}