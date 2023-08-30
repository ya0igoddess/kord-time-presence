package com.github.ya0igoddess.dbsync

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.migration.loadLiquibase
import com.github.ya0igoddess.dbsync.repositories.IDiscordGuildRepoService
import com.github.ya0igoddess.dbsync.repositories.IDiscordUserRepoService
import com.kotlindiscord.kord.extensions.events.ExtensionStateEvent
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.ExtensionState
import com.kotlindiscord.kord.extensions.extensions.event
import com.kotlindiscord.kord.extensions.utils.respond
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.event.guild.MemberJoinEvent
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.flow.collect
import org.koin.core.component.inject
import java.util.logging.Logger

class DBSyncExtension: Extension() {
    override val name: String = "kord-db-sync"

    override suspend fun setup() {
        getKoin().loadModules(listOf(DBSyncModule))

        val userService: IDiscordUserRepoService by inject()
        val guildService: IDiscordGuildRepoService by inject()
        val settings: KordDBSettings by inject()

        loadLiquibase(settings.jdbc!!, name, "db/changelog/kord-db-sync/main-changelog.xml")

//        event<MemberJoinEvent> {
//            action {
//                userService.createFromExternalEntity(event.member.asUser())
//            }
//        }

//        event<GuildCreateEvent> {
//            action {
//                guildService.getOrCreateFromExternal(event.guild)
//            }
//        }
    }
}