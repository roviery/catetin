package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Deadline")
data class DeadlineEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deadlineId")
    var deadlineId: Int,

    @NonNull
    @ColumnInfo(name = "deadlineDate")
    var deadlineDate: String,

    @NonNull
    @ColumnInfo(name = "deadlineNotes")
    var deadlineNotes: String,

)