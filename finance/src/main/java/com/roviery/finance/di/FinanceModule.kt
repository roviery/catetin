package com.roviery.finance.di

import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.usecase.*
import com.roviery.finance.FinanceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FinanceDetailUseCase> { FinanceDetailInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FinanceViewModel(get()) }
}