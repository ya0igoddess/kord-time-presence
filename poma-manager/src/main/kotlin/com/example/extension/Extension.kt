package com.example.extension

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.example.config.PomaModule
import com.example.database.tables
import com.example.handlers.SampleHandler
import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import org.koin.core.component.inject

class PomaManagerExt: Extension() {

    companion object {
        const val code = "poma_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        getKoin().loadModules(listOf(PomaModule))

        val databaseModule: SkaardModuleDatabase by inject()
        databaseModule.addTables(tables)
        val settings: KordDBSettings by inject()
        loadLiquibase(settings.jdbc!!, name, "changelog/poma-manager/main-changelog.xml")

        val sampleHandler: SampleHandler by inject()

        event<ChatCommandInvocationEvent> {
            action {
                sampleHandler.handle(event)
            }
        }
    }

}