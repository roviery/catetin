package com.roviery.catetin.di

import android.app.Application
import androidx.room.Room
import com.roviery.catetin.feature_user.data.data_source.UserDatabase
import com.roviery.catetin.feature_user.data.repository.UserRepositoryImpl
import com.roviery.catetin.feature_user.domain.repository.UserRepository
import com.roviery.catetin.feature_user.domain.use_case.AddUser
import com.roviery.catetin.feature_user.domain.use_case.DeleteUser
import com.roviery.catetin.feature_user.domain.use_case.GetUser
import com.roviery.catetin.feature_user.domain.use_case.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repository),
            addUser = AddUser(repository),
            deleteUser = DeleteUser(repository),
        )
    }
}