package com.roviery.catetin.feature_user.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roviery.catetin.feature_user.domain.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}