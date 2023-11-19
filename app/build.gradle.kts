
plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation(project(":database-sync"))
    implementation(project(":presence-monitor"))
    implementation(project(":sample-module"))
    implementation(kotlin("stdlib-jdk8"))

    runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
}