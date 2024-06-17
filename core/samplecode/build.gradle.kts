tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {

//    // JPA
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//
//    // https://mvnrepository.com/artifact/io.github.openfeign.querydsl/querydsl-jpa
//    implementation("io.github.openfeign.querydsl:querydsl-jpa:6.3")
//    kapt("io.github.openfeign.querydsl:querydsl-apt:6.3:jpa")
//
//    // mysql
//    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
//    implementation("mysql:mysql-connector-java:8.0.33")


    implementation(project(":components:yamlreader"))
    implementation(project(":components:jpa"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
