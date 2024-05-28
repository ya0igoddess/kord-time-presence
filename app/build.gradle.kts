val kordExVersion: String by properties

plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:$kordExVersion")
    implementation(project(":database-sync"))
    implementation(project(":presence-monitor"))
    implementation(project(":sample-module"))
    implementation(project(":toxicity-analyzer"))
    implementation(project(":music-quiz"))
    runtimeOnly("ch.qos.logback:logback-classic:1.4.7")
    runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    setProperty("zip64", true)
    manifest.attributes["Main-Class"] = "MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies) {
        exclude("META-INF/*.RSA", "META-INF/*.DSA", "META-INF/*.SF")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}