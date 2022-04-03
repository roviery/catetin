package com.roviery.catetin.di

import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.usecase.DeadlineInteractor
import com.roviery.core.domain.usecase.DeadlineUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    factory<DeadlineUseCase> {DeadlineInteractor(get())}
}

val viewModelModule = module{
    viewModel { HomeViewModel(get())}
}