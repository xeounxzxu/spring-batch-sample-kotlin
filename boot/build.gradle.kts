tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-batch")
    testImplementation("org.springframework.batch:spring-batch-test")

    // mysql
    implementation(project(":data:mysql"))
    testImplementation(project(":data:mysql"))

    // h2
    // todo : implementation vs runtimeOnly
    implementation(project(":data:h2"))
    testImplementation(project(":data:h2"))
}
