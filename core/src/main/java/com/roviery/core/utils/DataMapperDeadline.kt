package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.DeadlineEntity
import com.roviery.core.domain.model.Deadline

object DataMapperDeadline {

    fun mapDomainToEntity(input: Deadline) =
        DeadlineEntity(
            deadlineId = input.deadlineId,
            deadlineDate = input.deadlineDate,
            deadlineNotes = input.deadlineNotes
        )

    fun mapEntitiesToDomain(input: List<DeadlineEntity>): List<Deadline> =
        input.map{
            Deadline(
                deadlineId = it.deadlineId,
                deadlineDate = it.deadlineDate,
                deadlineNotes = it.deadlineNotes
            )
        }
}