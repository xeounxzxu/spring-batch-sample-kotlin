package com.example.basic.web

import com.example.basic.core.util.I18nMsgUtil
import com.example.basic.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController constructor(
    private val itemService: ItemService,
    private val i18nMsgUtil: I18nMsgUtil,
) {
    @GetMapping("/message")
    fun getMessage() {
        throw NullPointerException()
    }

    @GetMapping("message2")
    fun getMessage(name: String) = i18nMsgUtil.getMessage("test", arrayOf(name))

    @GetMapping("/api/hello")
    fun showByString(): String = "hello"

    @GetMapping("/my-item")
    fun getMyItemName(): String = itemService.getMyItemName()

    @GetMapping("/other-item")
    fun getOtherItem(): String = itemService.getOtherItemName()
}
