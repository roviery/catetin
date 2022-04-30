package com.roviery.finance.di

import com.roviery.finance.FinanceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val financeModule = module {
    viewModel { FinanceViewModel(get(), get()) }
}