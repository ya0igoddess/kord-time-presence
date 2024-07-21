/*
 * This file was generated by the Gradle 'init' task.
 */

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("io.kotest") version "0.4.11" apply false
    }
}

dependencyResolutionManagement {

    val kordExVersion: String by settings
    val koTySaVersion: String by settings
    val kodeinVersion: String by settings
    val kotestVersion: String by settings
    val mockkVersion: String by settings

    versionCatalogs {
        create("compileLibs") {
            library("kordEx", "com.kotlindiscord.kord.extensions:kord-extensions:$kordExVersion")
            library("kotysa", "org.ufoss.kotysa:kotysa-r2dbc:$koTySaVersion")
            library("kodeinDi", "org.kodein.di:kodein-di:$kodeinVersion")
            library("kodeinConf", "org.kodein.di:kodein-di-conf:$kodeinVersion")

            library("kotest.assertions", "io.kotest:kotest-assertions-core:$kotestVersion")
            library("kotest.engine.jvm", "io.kotest:kotest-framework-engine-jvm:$kotestVersion")
            library("kotest.runner.junit", "io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
            library("mockk", "io.mockk:mockk:$mockkVersion")
            bundle(
                "moduleLibs", listOf(
                    "kordEx", "kotysa", "kodeinDi", "kodeinConf", "kotest.assertions", "kotest.engine.jvm",
                    "kotest.runner.junit", "mockk"
                )
            )
        }
    }
}

include("database-sync")
include("sample-module")
include("presence-monitor")
include("toxicity-analyzer")
include("music-quiz")
include("tasks-module")
include("analytics-module")
include("app")