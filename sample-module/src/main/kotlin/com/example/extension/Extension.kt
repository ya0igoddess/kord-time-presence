package com.example.extension

import com.example.config.SampleModule
import com.example.database.sampleTables
import com.example.handlers.SampleHandler
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.kotlindiscord.kord.extensions.extensions.event
import org.kodein.di.instance

class SampleExtension: KodeinExtension() {

    companion object {
        const val code = "sample_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(SampleModule)
        val databaseModule: SkaardModuleDatabase by di.instance()
        databaseModule.addTables(sampleTables)
        val settings: KordDBSettings by di.instance()
        loadLiquibase(settings.jdbc!!, name, "changelog/sample-module/main-changelog.xml")

        val sampleHandler: SampleHandler by di.instance()

        event<ChatCommandInvocationEvent> {
            action {
                sampleHandler.handle(event)
            }
        }
    }

}