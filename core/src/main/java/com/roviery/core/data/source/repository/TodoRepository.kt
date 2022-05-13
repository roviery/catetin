package com.roviery.core.data.source.repository

import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.domain.model.Todo
import com.roviery.core.domain.repository.ITodoRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITodoRepository {

    override fun getAllTodo(status: String): Flow<List<Todo>> =
        localDataSource.getAllTodo(status).map {
            DataMapperTodo.mapEntitiesToDomain(it)
        }

    override fun insertTodo(todo: Todo) {
        val todoEntity = DataMapperTodo.mapDomainToEntity(todo)
        appExecutors.diskIO().execute { localDataSource.insertTodo(todoEntity) }
    }

    override fun updateTodo(todo: Todo, newStatus: String, newMessage: String) {
        val todoEntity = DataMapperTodo.mapDomainToEntity(todo)
        appExecutors.diskIO()
            .execute { localDataSource.updateTodo(todoEntity, newStatus, newMessage) }
    }

    override fun deleteTodo(todo: Todo) {
        val todoEntity = DataMapperTodo.mapDomainToEntity(todo)
        appExecutors.diskIO().execute { localDataSource.deleteTodo(todoEntity) }
    }
}