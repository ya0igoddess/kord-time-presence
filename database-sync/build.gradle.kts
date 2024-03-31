val kordExVersion: String by properties
val ktomlVersion: String by properties
val koTySaVersion: String by properties
val kodeinVersion: String by properties
val kotestVersion: String by properties
val mockkVersion: String by properties

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.kotlindiscord.kord.extensions:kord-extensions:$kordExVersion")
    api("org.kodein.di:kodein-di:$kodeinVersion")
    api("org.kodein.di:kodein-di-conf:$kodeinVersion")
    implementation("com.akuleshov7:ktoml-core:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-source:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-file:$ktomlVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

    implementation("org.ufoss.kotysa:kotysa-r2dbc:$koTySaVersion")

    implementation("org.liquibase:liquibase-core:4.23.1")

    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-engine-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}