import org.gradle.api.Project.DEFAULT_VERSION

plugins {
    kotlin("jvm") version "1.4.20"
    kotlin("kapt") version "1.4.20"
    id("io.johnsonlee.sonatype-publish-plugin") version "1.5.6"
}

group = "io.johnsonlee"
version = project.findProperty("version")?.takeIf { it != DEFAULT_VERSION } ?: "1.0.0-SNAPSHOT"


repositories {
    mavenLocal()
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("bom"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
