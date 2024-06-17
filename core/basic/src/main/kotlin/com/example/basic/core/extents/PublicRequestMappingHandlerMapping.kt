package com.example.basic.core.extents

import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method

class PublicRequestMappingHandlerMapping : RequestMappingHandlerMapping() {
    override fun getMappingForMethod(
        method: Method,
        handlerType: Class<*>,
    ): RequestMappingInfo? = getMappingForPublicAPI(method, super.getMappingForMethod(method, handlerType))

    private fun getMappingForPublicAPI(
        method: Method,
        info: RequestMappingInfo?,
    ): RequestMappingInfo? {
        val publicApi: PublicAPI? =
            AnnotatedElementUtils.findMergedAnnotation(
                method,
                PublicAPI::class.java,
            )

        return if (publicApi != null && info != null) {
            createdRequestMappingInfo(publicApi, method, info)
        } else if (publicApi == null && info != null) {
            info
        } else {
            null
        }
    }

    private fun createdRequestMappingInfo(
        publicApi: PublicAPI,
        method: Method,
        info: RequestMappingInfo,
    ): RequestMappingInfo {
        val prefix = publicApi.prefix
        val isCoercionAble = publicApi.isCoercionAble

        return if (isCoercionAble) {
            RequestMappingInfo
                .paths(prefix)
                .options(
                    RequestMappingInfo.BuilderConfiguration()
                        .apply {
                            this.setContentNegotiationManager(super.getContentNegotiationManager())
                            this.patternParser = super.getPatternParser()
                        },
                )
                .build()
                .combine(info)
        } else {
            val requestMapping: RequestMapping =
                AnnotatedElementUtils.findMergedAnnotation(
                    method,
                    RequestMapping::class.java,
                )!!

            val olds = info.patternValues

            val news = olds.map { "${prefix}$it" }.toMutableSet()

            news.addAll(olds)

            RequestMappingInfo.paths(*super.resolveEmbeddedValuesInPatterns(news.toTypedArray()))
                .methods(*requestMapping.method)
                .params(*requestMapping.params)
                .headers(*requestMapping.headers)
                .consumes(*requestMapping.consumes)
                .produces(*requestMapping.produces)
                .mappingName(requestMapping.name)
                .options(
                    RequestMappingInfo.BuilderConfiguration()
                        .apply {
                            this.setContentNegotiationManager(super.getContentNegotiationManager())
                            this.patternParser = super.getPatternParser()
                        },
                )
                .build()
        }
    }
}
