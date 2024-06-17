package com.example.basic.core.util

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class I18nMsgUtil constructor(
    private val messageSource: MessageSource,
) {
    fun getMessage(code: String): String =
        messageSource.getMessage(
            code,
            arrayOf<String>(),
            LocaleContextHolder.getLocale(),
        )

    fun getMessage(
        code: String,
        args: Array<String>,
    ) = messageSource.getMessage(
        code,
        args,
        LocaleContextHolder.getLocale(),
    )
}
