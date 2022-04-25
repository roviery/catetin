package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "FinanceDetail", foreignKeys = [ForeignKey(
        entity = FinanceEntity::class,
        parentColumns = arrayOf("FType"),
        childColumns = arrayOf("FDType"),
        onDelete = ForeignKey.CASCADE
    )]
)
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