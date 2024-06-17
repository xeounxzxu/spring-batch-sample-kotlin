package com.example.example.extension

import com.example.example.restdocs.manualRestDocumentation
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

class MockMvcExtension

val converter = MappingJackson2HttpMessageConverter(ObjectMapper())

suspend fun mockMvc(
    controller: Any,
    httpMessageConverter: AbstractJackson2HttpMessageConverter? = converter,
): MockMvcRequestSpecification {
    val mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(
                    manualRestDocumentation(),
                ),
            )
            .setMessageConverters(
                httpMessageConverter,
            ).alwaysDo<StandaloneMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()

    return RestAssuredMockMvc.given().mockMvc(mockMvc)
}
