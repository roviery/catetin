package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Todo
import com.roviery.core.domain.repository.ITodoRepository
import kotlinx.coroutines.flow.Flow

class TodoInteractor(private val todoRepository: ITodoRepository) : TodoUseCase {
    override fun getAllTodo(status: String): Flow<List<Todo>> = todoRepository.getAllTodo(status)

    override fun insertTodo(todo: Todo) = todoRepository.insertTodo(todo)

    override fun updateTodo(todo: Todo, newStatus: String, newMessage: String) =
        todoRepository.updateTodo(todo, newStatus, newMessage)

    override fun deleteTodo(todo: Todo) = todoRepository.deleteTodo(todo)

}