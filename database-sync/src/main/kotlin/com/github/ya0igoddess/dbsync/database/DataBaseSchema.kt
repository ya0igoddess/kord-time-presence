package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.model.discord.DiscordUser
import com.github.ya0igoddess.dbsync.model.discord.DiscordGuild
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.tables

object Users: PostgresqlTable<DiscordUser>("discord_user") {
    val id = bigInt({ it.id.toLong() })
        .primaryKey()
    val name = varchar(DiscordUser::name)
}

object Guilds: PostgresqlTable<DiscordGuild>("discord_guild") {
    val id = bigInt({it.id.toLong()})
        .primaryKey()
    val name = varchar(DiscordGuild::name)
}

val tables = tables().postgresql(Users, Guilds)