package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quicknotes")
data class QuicknotesEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quicknotesId")
    var quicknotesId: Int,

    @NonNull
    @ColumnInfo(name = "quicknotesText")
    var quicknotesText: String,
)