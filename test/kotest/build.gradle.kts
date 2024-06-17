tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {

    // kotest dependencies add
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${property("kotestVersion")}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${property("kotestVersion")}")
    testImplementation("io.kotest:kotest-property:${property("kotestVersion")}")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:${property("extensionsSpringVersion")}")

    // mockk dependencies add
    testImplementation("io.mockk:mockk:1.13.9")

    testImplementation(project(":test:apidocs"))
}
