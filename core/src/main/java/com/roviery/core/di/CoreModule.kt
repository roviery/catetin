package com.roviery.core.di

import androidx.room.Room
import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.data.source.local.room.CatetinDatabase
import com.roviery.core.data.source.repository.*
import com.roviery.core.domain.repository.*
import com.roviery.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<CatetinDatabase>().catetinDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatetinDatabase::class.java,
            "Catetin.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries() // -> for type financial detail input
            .createFromAsset("database/finance.db")
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IDeadlineRepository> {
        DeadlineRepository(
            get(),
            get()
        )
    }
    single<IQuicknotesRepository> {
        QuicknotesRepository(
            get(),
            get()
        )
    }
    single<IFinanceRepository> {
        FinanceRepository(
            get(),
            get()
        )
    }
    single<IFinanceDetailRepository> {
        FinanceDetailRepository(
            get(),
            get()
        )
    }
    single<ITodoRepository> {
        TodoRepository(
            get(),
            get()
        )
    }
}