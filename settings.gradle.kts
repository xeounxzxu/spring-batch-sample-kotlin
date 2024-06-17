rootProject.name = "kotlin-core"

include(
    ":core:basic",
    ":core:dynamicbean",
    ":core:virtualthread",
    ":core:samplecode",
)

include(
    ":r2dbc",
    ":r2dbc:callback",
    ":r2dbc:data",
)

include(
    ":test",
    ":test:kotest",
    ":test:apidocs",
)

include(
    ":querydsl:openfeign",
)

include(
    ":components",
    ":components:yamlreader",
    ":components:jpa"
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val asciidoctorConvertVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "plugin.allopen" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.allopen" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.asciidoctor.jvm.convert" -> useVersion(asciidoctorConvertVersion)
            }
        }
    }
}
