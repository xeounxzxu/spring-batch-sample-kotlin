tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

dependencies {
    runtimeOnly("mysql:mysql-connector-java")
}
