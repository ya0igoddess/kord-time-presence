package com.github.ya0igoddess.dbsync.extensions

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.database.kordDBSyncTables
import com.github.ya0igoddess.dbsync.extensions.dataupdates.DataSyncExtension
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.kotlindiscord.kord.extensions.extensions.Extension
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules


class DBSyncExtension: Extension() {

    companion object {
        const val code = "kord_db_sync"
    }

    override val name: String = code

    override suspend fun setup() {

        val databaseModule: SkaardModuleDatabase by inject()
        val settings: KordDBSettings by inject()
        databaseModule.addTables(kordDBSyncTables)

        loadLiquibase(settings.jdbc!!, name, "db/changelog/kord-db-sync/main-changelog.xml")

        bot.addExtension(::DataSyncExtension)
    }
}