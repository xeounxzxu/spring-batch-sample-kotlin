package io.example.dataserving.job.dto

data class MsgDTO(
    private val status: Int,
    private val data: Any
) {

    fun getStatus() = this.status

    fun getData() = this.data
}
