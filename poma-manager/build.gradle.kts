
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.6")
    implementation(project(":database-sync"))
    implementation("org.ufoss.kotysa:kotysa-r2dbc:3.1.0")

    implementation(kotlin("stdlib-jdk8"))
}
kotlin {
    jvmToolchain(17)
}