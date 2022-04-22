package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.QuicknotesEntity
import com.roviery.core.domain.model.Quicknotes

object DataMapperQuicknotes {

    fun mapDomainToEntity(input: Quicknotes) =
        QuicknotesEntity(
            quicknotesId = input.quicknotesId,
            quicknotesText = input.quicknotesText
        )

    fun mapEntitiesToDomain(input: List<QuicknotesEntity>): List<Quicknotes> =
        input.map {
            Quicknotes(
                quicknotesId = it.quicknotesId,
                quicknotesText = it.quicknotesText
            )
        }
}