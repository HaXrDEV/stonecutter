plugins {
    java
    kotlin("jvm")
    alias(libs.plugins.intellij)
}

group = "dev.kikugie"
version = "0.1.0-beta.1"

repositories {
    mavenCentral()
    maven("https://repo.gradle.org/gradle/libs-releases")
}

dependencies {
    implementation(project(":stitcher"))
    implementation(project(":stonecutter"))
    implementation("org.gradle:gradle-tooling-api:8.8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.5.1")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.10")
}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName = "stonecutter-dev"
    version = "2024.1.4"
    type = "IC" // Target IDE Platform
    plugins = listOf("com.intellij.java", "org.jetbrains.plugins.gradle")
}

tasks.patchPluginXml {
    sinceBuild = "223"
    untilBuild = "242.*"
}