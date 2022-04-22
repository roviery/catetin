package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quicknotes(
    val quicknotesId: Int,
    val quicknotesText: String
) : Parcelable
