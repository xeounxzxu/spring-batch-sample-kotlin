package com.example.basic.core.exception

data class MsgDTO constructor(
    private var code: String?,
    private var message: String?,
) {
    fun getCode() = this.code

    fun getMessage() = this.message

    constructor(code: String) : this(code, null) {}

    constructor() : this(null, null)
}
