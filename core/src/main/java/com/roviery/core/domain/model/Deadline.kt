package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deadline(
    val deadlineId: Int,
    val deadlineDate: String,
    val deadlineNotes: String
): Parcelable