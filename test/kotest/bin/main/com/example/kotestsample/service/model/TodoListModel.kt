package com.example.kotestsample.service.model

data class TodoListModel(
    val title: String,
    val content: String,
) {
    companion object {
        fun getFaker(): List<TodoListModel> {
            return listOf(
                TodoListModel(
                    title = "test",
                    content = "test1",
                ),
                TodoListModel(
                    title = "test",
                    content = "test1",
                ),
                TodoListModel(
                    title = "test",
                    content = "test1",
                ),
            )
        }

        fun getFaker(id: Long): TodoListModel {
            return TodoListModel(
                title = "test-$id",
                content = "test",
            )
        }
    }
}
