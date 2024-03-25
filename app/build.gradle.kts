
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

    runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
}

application {
    mainClass.set("MainKt")
}