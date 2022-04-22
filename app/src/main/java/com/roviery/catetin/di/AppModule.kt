package com.roviery.catetin.di

import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.usecase.DeadlineInteractor
import com.roviery.core.domain.usecase.DeadlineUseCase
import com.roviery.core.domain.usecase.QuicknotesInteractor
import com.roviery.core.domain.usecase.QuicknotesUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    factory<DeadlineUseCase> {DeadlineInteractor(get())}
    factory<QuicknotesUseCase> {QuicknotesInteractor(get())}
}

val viewModelModule = module{
    viewModel { HomeViewModel(get(), get())}
}