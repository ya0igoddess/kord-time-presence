val kordExVersion: String by properties
val ktomlVersion: String by properties

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(compileLibs.bundles.moduleLibs)
    implementation("com.akuleshov7:ktoml-core:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-source:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-file:$ktomlVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")
    implementation("org.liquibase:liquibase-core:4.23.1")
}