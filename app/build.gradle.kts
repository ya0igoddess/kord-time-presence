val kordExVersion: String by properties

plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:$kordExVersion")
    implementation(project(":database-sync"))
    implementation(project(":presence-monitor"))
    implementation(project(":sample-module"))
    implementation(project(":toxicity-analyzer"))

    runtimeOnly("ch.qos.logback:logback-classic:1.4.7")
    runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
}

application {
    mainClass.set("MainKt")
}