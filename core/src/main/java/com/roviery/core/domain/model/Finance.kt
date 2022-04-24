package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Finance(
    val financeType: String,
    val financeFundAllocation: Int
) : Parcelable