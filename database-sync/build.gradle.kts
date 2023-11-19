
val kordExVersion: String by properties

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.kotlindiscord.kord.extensions:kord-extensions:$kordExVersion")
    implementation("com.akuleshov7:ktoml-core:0.5.0")
    implementation("com.akuleshov7:ktoml-source:0.5.0")
    implementation("com.akuleshov7:ktoml-file:0.5.0")

    implementation("org.ufoss.kotysa:kotysa-r2dbc:3.1.0")

    implementation("org.liquibase:liquibase-core:4.23.1")
    implementation(kotlin("stdlib-jdk8"))
}
kotlin {
    jvmToolchain(17)
}