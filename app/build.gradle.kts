
plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.6")
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation(project(":database-sync"))

}
