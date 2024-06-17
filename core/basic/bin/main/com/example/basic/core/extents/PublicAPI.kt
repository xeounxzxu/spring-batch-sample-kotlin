package com.example.basic.core.extents

@Target(AnnotationTarget.FUNCTION)
annotation class PublicAPI(val prefix: String = "public", val isCoercionAble: Boolean = false)
