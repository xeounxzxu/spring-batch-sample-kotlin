package com.example.kotestsample.service

import com.example.kotestsample.service.model.TodoListModel
import org.springframework.stereotype.Service

@Service
class TodoListService {
    fun get(): List<TodoListModel> {
        return TodoListModel.getFaker()
    }

    fun getOne(id: Long): TodoListModel = TodoListModel.getFaker(id)

    fun save(model: TodoListModel): Unit = Unit

    fun delete(id: Long): Unit = Unit
}
