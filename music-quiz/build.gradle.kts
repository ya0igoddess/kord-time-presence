plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation(compileLibs.bundles.moduleLibs)
    implementation(project(":database-sync"))
    implementation("dev.kord:kord-voice:0.13.1")
    implementation("dev.arbjerg:lavaplayer:2.1.2")
}