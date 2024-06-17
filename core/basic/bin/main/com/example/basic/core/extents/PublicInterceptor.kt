package com.example.basic.core.extents

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor

private val logger = LoggerFactory.getLogger(PublicInterceptor::class.java)

class PublicInterceptor : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        return super.preHandle(request, response, handler)
    }
}
