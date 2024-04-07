package com.github.ya0igoddess.presencemonitoring.extension

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.github.ya0igoddess.dbsync.repositories.IDiscordChannelRepoService
import com.github.ya0igoddess.presencemonitoring.config.PresenceMonitoringModule
import com.github.ya0igoddess.presencemonitoring.database.presenceMonitoringTables
import com.github.ya0igoddess.presencemonitoring.handlers.RegisterGetTotalActivityByPeriod
import com.github.ya0igoddess.presencemonitoring.handlers.RegisterGetUniqueUsersByPeriodCommand
import com.github.ya0igoddess.presencemonitoring.handlers.VoiceStatusChangeHandler
import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionUserService
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.event.user.VoiceStateUpdateEvent
import org.kodein.di.instance

class PresenceMonitorExtension: KodeinExtension() {

    companion object {
        const val code = "kord_presence_monitor"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(PresenceMonitoringModule)

        val databaseModule: SkaardModuleDatabase by di.instance()
        databaseModule.addTables(presenceMonitoringTables)
        val settings: KordDBSettings by di.instance()
        loadLiquibase(settings.jdbc!!, name, "changelog/kord-presence-monitor/main-changelog.xml")

        val voiceStatusChangeHandler: VoiceStatusChangeHandler by di.instance()

        event<VoiceStateUpdateEvent> {
            action {
                voiceStatusChangeHandler.handle(event)
            }
        }

        val voiceConnectionUserService: IVoiceConnectionUserService by di.instance()
        val channelRepoService: IDiscordChannelRepoService by di.instance()
        RegisterGetUniqueUsersByPeriodCommand(voiceConnectionUserService, channelRepoService)
        RegisterGetTotalActivityByPeriod(voiceConnectionUserService, channelRepoService)

    }

}