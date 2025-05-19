package com.example.myapplication.repository

import com.example.myapplication.data.DatabaseModule
import com.example.myapplication.data.TodoDao
import com.example.myapplication.data.TodoEntity
import com.example.myapplication.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

object TodoRepository {
    private val dao: TodoDao
        get() = DatabaseModule
            .getDatabase(com.example.myapplication.MyApplication.instance)
            .todoDao()

    fun cachedTodos(): Flow<List<TodoEntity>> =
        dao.getAll()

    suspend fun refreshFromNetwork() {
        val remoteList = RetrofitInstance.api.getTodos()
        val entities = remoteList.map { net ->
            TodoEntity(
                TITLE     = net.title,
                ID        = net.id,
                USERID    = net.userId,
                COMPLETED = net.completed
            )
        }
        dao.clear()
        dao.insertAll(entities)
    }
}
