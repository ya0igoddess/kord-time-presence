package com.github.ya0igoddess.presencemonitoring.extension

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.github.ya0igoddess.presencemonitoring.config.PresenceMonitoringModule
import com.kotlindiscord.kord.extensions.extensions.Extension
import org.koin.core.component.inject

class PresenceMonitorExtension: Extension() {

    companion object {
        const val code = "kord_presence_monitor"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        getKoin().loadModules(listOf(PresenceMonitoringModule))

        val settings: KordDBSettings by inject()
        loadLiquibase(settings.jdbc!!, name, "changelog/kord-presence-monitor/main-changelog.xml")

    }

}