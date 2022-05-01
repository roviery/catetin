package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Finance")
data class FinanceEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "FID")
    var financeId: Int,

    @NonNull
    @ColumnInfo(name = "FType")
    var financeType: String,

    @NonNull
    @ColumnInfo(name = "FFundAllocation")
    var financeFundAllocation: Int,

    @NonNull
    @ColumnInfo(name = "FUsedFund")
    var financeUsedFund: Int,

    @NonNull
    @ColumnInfo(name = "FRemainingFund")
    var financeRemainingFund: Int

)
