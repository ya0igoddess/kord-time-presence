package com.github.ya0igoddess.dbsync

import com.github.ya0igoddess.dbsync.database.Users
import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.kotlindiscord.kord.extensions.commands.chat.ChatCommandRegistry
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import com.kotlindiscord.kord.extensions.utils.loadModule
import dev.kord.core.event.message.MessageCreateEvent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.koinApplication
import org.ufoss.kotysa.R2dbcSqlClient

class DBSyncExtension: Extension() {
    override val name: String = "db-sync"

    override suspend fun setup() {
        getKoin().loadModules(listOf(dataBaseModule))
    }
}