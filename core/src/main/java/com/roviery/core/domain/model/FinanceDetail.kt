package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FinanceDetail(
    var financeDetailId: Int,
    var financeDetailType: String,
    var financeDetailName: String,
    var financeDetailExpense: Int
) : Parcelable