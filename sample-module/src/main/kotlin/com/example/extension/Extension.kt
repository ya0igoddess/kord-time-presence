package com.example.extension

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.example.config.SampleModule
import com.example.database.sampleTables
import com.example.handlers.SampleHandler
import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import org.koin.core.component.inject

class SampleExtension: Extension() {

    companion object {
        const val code = "sample_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        //getKoin().loadModules(listOf(SampleModule))

        val databaseModule: SkaardModuleDatabase by inject()
        databaseModule.addTables(sampleTables)
        val settings: KordDBSettings by inject()
        loadLiquibase(settings.jdbc!!, name, "changelog/sample-module/main-changelog.xml")

        val sampleHandler: SampleHandler by inject()

        event<ChatCommandInvocationEvent> {
            action {
                sampleHandler.handle(event)
            }
        }
    }

}