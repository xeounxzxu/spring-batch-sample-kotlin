package com.example.kotestsample.web

import com.example.kotestsample.service.TodoListService
import com.example.kotestsample.web.dto.TodoListAllResponse
import com.example.kotestsample.web.dto.TodoListRequest
import com.example.kotestsample.web.dto.TodoListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/todo-list")
class TodoListController(
    private val service: TodoListService,
) {
    @GetMapping
    fun get(): TodoListAllResponse {
        return service.get().map {
            TodoListResponse.of(it)
        }.run {
            TodoListAllResponse.of(
                this,
            )
        }
    }

    @GetMapping("/{id}")
    fun getOne(
        @PathVariable id: Long,
    ): TodoListResponse {
        return service.getOne(id).run {
            TodoListResponse.of(this)
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun save(
        @RequestBody request: TodoListRequest,
    ) {
        service.save(request.toModel())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: TodoListRequest,
    ) {
        service.save(request.toModel())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(
        @PathVariable id: Long,
    ) {
        service.delete(id)
    }
}
