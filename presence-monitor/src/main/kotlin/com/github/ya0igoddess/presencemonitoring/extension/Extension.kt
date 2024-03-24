package com.github.ya0igoddess.presencemonitoring.extension

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.database.kordDBSyncTables
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.github.ya0igoddess.presencemonitoring.config.PresenceMonitoringModule
import com.github.ya0igoddess.presencemonitoring.database.presenceMonitoringTables
import com.github.ya0igoddess.presencemonitoring.handlers.VoiceStatusChangeHandler
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.event.user.VoiceStateUpdateEvent
import org.koin.core.component.inject

class PresenceMonitorExtension: Extension() {

    companion object {
        const val code = "kord_presence_monitor"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        //getKoin().loadModules(listOf(PresenceMonitoringModule))

        val databaseModule: SkaardModuleDatabase by inject()
        databaseModule.addTables(presenceMonitoringTables)
        val settings: KordDBSettings by inject()
        loadLiquibase(settings.jdbc!!, name, "changelog/kord-presence-monitor/main-changelog.xml")

        val voiceStatusChangeHandler: VoiceStatusChangeHandler by inject()

        event<VoiceStateUpdateEvent> {
            action {
                voiceStatusChangeHandler.handle(event)
            }
        }
    }

}