package com.example.kotestsample.web.dto

import com.example.kotestsample.service.model.TodoListModel

class TodoListDto

data class TodoListRequest(
    val title: String,
    val content: String,
) {
    fun toModel(): TodoListModel =
        TodoListModel(
            this.title,
            this.content,
        )
}

data class TodoListResponse(
    val title: String,
    val content: String,
) {
    companion object {
        fun of(model: TodoListModel): TodoListResponse {
            return TodoListResponse(
                model.title,
                model.content,
            )
        }
    }
}

data class TodoListAllResponse(
    val list: List<TodoListResponse>,
) {
    companion object {
        fun of(list: List<TodoListResponse>): TodoListAllResponse {
            return TodoListAllResponse(list)
        }
    }
}
