plugins {
    kotlin("jvm") version "1.4.31"
}

group = "de.hjanssen"
version = "0.0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.+")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.+")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.+")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}