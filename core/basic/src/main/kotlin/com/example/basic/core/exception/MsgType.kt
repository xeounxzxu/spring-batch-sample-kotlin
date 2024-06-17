package com.example.basic.core.exception

enum class MsgType constructor(
    private var code: String,
) {
    SystemError("S001"),
    ;

    fun getCode() = this.code
}
