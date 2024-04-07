plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(compileLibs.bundles.moduleLibs)
    implementation(project(":database-sync"))
}