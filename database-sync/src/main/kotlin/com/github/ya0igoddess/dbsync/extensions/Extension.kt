package com.github.ya0igoddess.dbsync.extensions

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.database.kordDBSyncTables
import com.github.ya0igoddess.dbsync.extensions.dataupdates.DataSyncExtension
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.kotlindiscord.kord.extensions.extensions.Extension
import org.kodein.di.instance
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules


class DBSyncExtension: KodeinExtension() {

    companion object {
        const val code = "kord_db_sync"
    }

    override val name: String = code

    override suspend fun setup() {
        di.addImport(DBSyncModule)

        val databaseModule: SkaardModuleDatabase by di.instance()
        val settings: KordDBSettings by di.instance()
        databaseModule.addTables(kordDBSyncTables)

        loadLiquibase(settings.jdbc!!, name, "db/changelog/kord-db-sync/main-changelog.xml")

        bot.addExtension(::DataSyncExtension)
    }
}