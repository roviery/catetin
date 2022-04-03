package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.repository.IDeadlineRepository

class DeadlineInteractor(private val deadlineRepository: IDeadlineRepository) : DeadlineUseCase {

    override fun getAllDeadline() = deadlineRepository.getAllDeadline()

    override fun insertDeadline(deadline: Deadline) = deadlineRepository.insertDeadline(deadline)

    override fun updateDeadline(deadline: Deadline, newDate: String, newDeadlineNotes: String) =
        deadlineRepository.updateDeadline(deadline, newDate, newDeadlineNotes)

    override fun deleteDeadline(deadline: Deadline) = deadlineRepository.deleteDeadline(deadline)


}