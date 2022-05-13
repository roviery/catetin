package com.roviery.core.domain.repository

import com.roviery.core.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun getAllTodo(status: String): Flow<List<Todo>>

    fun insertTodo(todo: Todo)

    fun updateTodo(todo: Todo, newStatus: String, newMessage: String)

    fun deleteTodo(todo: Todo)
}