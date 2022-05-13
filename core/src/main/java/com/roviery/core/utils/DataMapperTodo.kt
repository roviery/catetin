package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.TodoEntity
import com.roviery.core.domain.model.Todo

object DataMapperTodo {

    fun mapDomainToEntity(input: Todo) =
        TodoEntity(
            todoId = input.todoId,
            todoStatus = input.todoStatus,
            todoMessage = input.todoMessage
        )

    fun mapEntitiesToDomain(input: List<TodoEntity>): List<Todo> =
        input.map {
            Todo(
                todoId = it.todoId,
                todoStatus = it.todoStatus,
                todoMessage = it.todoMessage
            )
        }
}