val koTySaVersion: String by properties
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
    implementation(project(":database-sync"))
    implementation("org.ufoss.kotysa:kotysa-r2dbc:$koTySaVersion")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}