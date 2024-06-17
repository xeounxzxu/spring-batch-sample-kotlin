tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.5.5")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

    // looger
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

    // jasypt
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:1.17")

//     mysql
//    implementation("mysql:mysql-connector-java")
    implementation("com.h2database:h2")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
