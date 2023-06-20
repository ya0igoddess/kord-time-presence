
plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.6")
    implementation("org.yaml:snakeyaml:1.21")

    implementation("org.ufoss.kotysa:kotysa-r2dbc:3.0.1")
}