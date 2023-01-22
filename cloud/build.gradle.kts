plugins {
    id("org.springframework.boot")
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":core"))
}
