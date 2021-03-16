import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotestVersion: String by project
val ktorVersion: String by project
val jacksonVersion: String by project

plugins {
    kotlin("jvm") version "1.4.31"

    id("io.kotlintest") version "1.1.1"

    jacoco
}

group = "de.hjanssen"
version = "0.0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-mock:$ktorVersion")

    testImplementation("ch.qos.logback:logback-classic:1.2.3")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()

    finalizedBy(tasks.findByName("jacocoTestReport"))
}

tasks.withType<JacocoReport> {
    reports {
        csv.isEnabled = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}