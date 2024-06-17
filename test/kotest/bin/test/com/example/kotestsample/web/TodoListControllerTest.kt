package com.example.kotestsample.web

import com.example.example.extension.mockMvc
import com.example.example.restdocs.DOCS_ROOT_PATH
import com.example.example.restdocs.RestDocExtension
import com.example.kotestsample.service.TodoListService
import com.example.kotestsample.service.model.TodoListModel
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.restassured.http.ContentType
import org.springframework.http.HttpStatus
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields

class TodoListControllerTest : BehaviorSpec() {
    init {

        extensions(SpringExtension, RestDocExtension)

        val service = mockk<TodoListService>()

        val controller = TodoListController(service)

        // /v1/todo-list
        Given("유저가 todo 리스트 목록을 조회를 할 때") {

            val given =
                mockMvc(controller)
                    .contentType(ContentType.JSON)

            When("정상적으로 조회가 된다면") {

                every {
                    service.get()
                } returns TodoListModel.getFaker()

                val call =
                    given
                        .get("/v1/todo-list")

                then("200 상태 코드 와 response 를 반환 한다") {

                    verify(exactly = 1) {
                        service.get()
                    }

                    call
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .apply(
                            document(
                                "${DOCS_ROOT_PATH}/todo-list/get-todo-list",
                                responseFields(
                                    fieldWithPath("list")
                                        .type(JsonFieldType.ARRAY)
                                        .description("todo 리스트 목록"),
                                    fieldWithPath("list[].title")
                                        .description("제목"),
                                    fieldWithPath("list[].content")
                                        .description("설명"),
                                ),
                            ),
                        )
                }
            }
        }

        // DELETE /v1/todo-list/{id}
        Given("유저가 todo 리스트를 삭제를 할 때") {

            val given =
                mockMvc(controller)
                    .contentType(ContentType.JSON)

            When("정상적으로 삭제 된다면") {

                every {
                    service.delete(any())
                } just runs

                val call =
                    given
                        .delete("/v1/todo-list/{id}", "1")

                Then("204 상태 코드를 내려준다.") {

                    verify(exactly = 1) {
                        service.delete(any())
                    }

                    call.then()
                        .statusCode(HttpStatus.NO_CONTENT.value())
                        .apply(
                            document("${DOCS_ROOT_PATH}/todo-list/remove"),
                        )
                }
            }
        }
    }
}
