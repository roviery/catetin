package com.roviery.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.model.Todo
import com.roviery.core.domain.usecase.TodoUseCase

class TodoViewModel(
    private val todoUseCase: TodoUseCase
) : ViewModel() {

    val todoList = todoUseCase.getAllTodo("todo").asLiveData()
    val inProgressList = todoUseCase.getAllTodo("in progress").asLiveData()
    val doneList = todoUseCase.getAllTodo("done").asLiveData()

    fun insertTodo(todo: Todo) = todoUseCase.insertTodo(todo)

    fun updateTodo(todo: Todo, newStatus: String, newMessages: String) =
        todoUseCase.updateTodo(todo, newStatus, newMessages)

    fun deleteTodo(todo: Todo) = todoUseCase.deleteTodo(todo)
}