package com.roviery.todo.di

import com.roviery.todo.TodoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    viewModel { TodoViewModel(get()) }
}