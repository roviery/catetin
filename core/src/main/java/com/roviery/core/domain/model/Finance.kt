package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Finance(
    val financeId: Int,
    val financeType: String,
    val financeFundAllocation: Int,
    val financeUsedFund: Int,
    val financeRemainingFund: Int
) : Parcelable