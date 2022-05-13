package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    var todoId: Int,
    var todoStatus: String,
    var todoMessage: String
) : Parcelable
