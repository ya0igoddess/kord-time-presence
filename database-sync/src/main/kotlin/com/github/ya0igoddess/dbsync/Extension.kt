package com.github.ya0igoddess.dbsync

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.kotlindiscord.kord.extensions.extensions.Extension

class DBSyncExtension: Extension() {
    override val name: String = "db-sync"

    override suspend fun setup() {
        getKoin().loadModules(listOf(DBSyncModule))
    }
}