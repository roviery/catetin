package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class TodoEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todoId")
    var todoId: Int,

    @NonNull
    @ColumnInfo(name = "todoStatus")
    var todoStatus: String,

    @NonNull
    @ColumnInfo(name = "todoMessage")
    var todoMessage: String
)
