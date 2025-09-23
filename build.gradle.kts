plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
}

group = "kr.jonghyun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Paperweight dev bundle
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")

    // MySQL Connector
    compileOnly("mysql:mysql-connector-java:8.0.33")
}

val targetJavaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
    sourceCompatibility = JavaVersion.toVersion(targetJavaVersion)
    targetCompatibility = JavaVersion.toVersion(targetJavaVersion)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(targetJavaVersion)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}