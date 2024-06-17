tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {

    compileOnly("jakarta.servlet:jakarta.servlet-api")
    compileOnly("org.springframework.boot:spring-boot-starter-test")

    api("org.springframework.restdocs:spring-restdocs-mockmvc")
    api("org.springframework.restdocs:spring-restdocs-restassured")
    api("io.rest-assured:spring-mock-mvc")

    // kotest dependencies add
    implementation("io.kotest:kotest-runner-junit5-jvm:${property("kotestVersion")}")
    implementation("io.kotest:kotest-assertions-core-jvm:${property("kotestVersion")}")
    implementation("io.kotest:kotest-property:${property("kotestVersion")}")
    implementation("io.kotest.extensions:kotest-extensions-spring:${property("extensionsSpringVersion")}")
}
