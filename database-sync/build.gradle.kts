val kordExVersion: String by properties
val ktomlVersion: String by properties
val koTySaVersion: String by properties
val kodeinVersion: String by properties

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
    implementation("com.akuleshov7:ktoml-core:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-source:$ktomlVersion")
    implementation("com.akuleshov7:ktoml-file:$ktomlVersion")

    implementation("org.ufoss.kotysa:kotysa-r2dbc:$koTySaVersion")

    implementation("org.liquibase:liquibase-core:4.23.1")
    implementation(kotlin("stdlib-jdk8"))
}