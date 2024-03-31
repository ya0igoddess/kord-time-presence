plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.kotest")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(compileLibs.bundles.moduleLibs)
    implementation(project(":database-sync"))
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")
}