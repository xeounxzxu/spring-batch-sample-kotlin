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
    implementation(project(":data"))
    implementation(project(":core"))
}
