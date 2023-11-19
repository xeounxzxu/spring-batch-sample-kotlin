plugins {
    id("org.springframework.boot")
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
}

dependencies {

    implementation(project(":cloud"))
    implementation(project(":data:mysql"))
    implementation(project(":core"))
    testImplementation(project(":cloud"))
    testImplementation(project(":data:mysql"))
    testImplementation(project(":core"))

}
