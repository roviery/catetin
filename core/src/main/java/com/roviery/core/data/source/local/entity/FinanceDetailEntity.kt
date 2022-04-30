package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FinanceDetail")
data class FinanceDetailEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "FDID")
    var financeDetailId: Int,

    @NonNull
    @ColumnInfo(name = "FDType")
    var financeDetailType: String,

    @NonNull
    @ColumnInfo(name = "FDName")
    var financeDetailName: String,

    @NonNull
    @ColumnInfo(name = "FDExpense")
    var financeDetailExpense: Int,
)
