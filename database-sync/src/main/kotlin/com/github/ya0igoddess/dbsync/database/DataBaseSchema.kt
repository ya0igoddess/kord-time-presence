package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.model.discord.DsUser
import com.github.ya0igoddess.dbsync.model.discord.DsGuild
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.tables

object Users: PostgresqlTable<DsUser>("discord_user") {
    val id = bigInt({ it.id.toLong() })
        .primaryKey()
    val name = varchar(DsUser::name)
}

object Guilds: PostgresqlTable<DsGuild>("discord_guild") {
    val id = bigInt({it.id.toLong()})
        .primaryKey()
    val name = varchar(DsGuild::name)
}

val tables = tables().postgresql(Users, Guilds)