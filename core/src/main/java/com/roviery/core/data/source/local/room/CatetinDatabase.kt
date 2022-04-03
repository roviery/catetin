package com.roviery.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roviery.core.data.source.local.entity.DeadlineEntity

@Database(entities = [DeadlineEntity::class], version = 1, exportSchema = false)
abstract class CatetinDatabase: RoomDatabase() {

    abstract fun catetinDao(): CatetinDao

}