package com.roviery.catetin.di

import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.usecase.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DeadlineUseCase> { DeadlineInteractor(get()) }
    factory<QuicknotesUseCase> { QuicknotesInteractor(get()) }
    factory<FinanceUseCase> { FinanceInteractor(get()) }
    factory<FinanceDetailUseCase> { FinanceDetailInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
}