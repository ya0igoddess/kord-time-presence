plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(compileLibs.bundles.moduleLibs)
    implementation(project(":database-sync"))
}