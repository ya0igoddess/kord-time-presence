package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.extensions.DBSyncExtension
import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.model.discord.DsGuild
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.model.discord.DsUser
import org.ufoss.kotysa.IndexType
import org.ufoss.kotysa.postgresql.PostgresqlTable

object Users: PostgresqlTable<DsUser>("${DBSyncExtension.code}.discord_user") {
    val id = bigInt(DsUser::id)
        .primaryKey()
    val name = varchar(DsUser::name)
}

object Guilds: PostgresqlTable<DsGuild>("${DBSyncExtension.code}.discord_guild") {
    val id = bigInt(DsGuild::id)
        .primaryKey()
    val name = varchar(DsGuild::name)
}

object Members: PostgresqlTable<DsMember>("${DBSyncExtension.code}.discord_member") {
    val id = bigSerial(DsMember::id)
        .primaryKey()
    val name = varchar(DsMember::name)
    val guildId = bigInt(DsMember::guildId, "guild_id")
        .foreignKey(Guilds.id)
    val userId = bigInt(DsMember::userId, "user_id")
        .foreignKey(Users.id)

    init {
        index(setOf(guildId, userId), type = IndexType.UNIQUE ,indexName =  "guild_user_index")
    }
}

object Channels: PostgresqlTable<DsChannel>("${DBSyncExtension.code}.discord_channel") {
    val id = bigInt(DsChannel::id)
        .primaryKey()
    val name = varchar(DsChannel::name)
    val guildId = bigInt(DsChannel::guildId, "guild_id")
        .foreignKey(Guilds.id)
}

data class FakeObj(val id: Long)
object FakeTable: PostgresqlTable<FakeObj>("") {
    val id = bigInt(FakeObj::id).primaryKey()
}

val kordDBSyncTables = listOf(Users, Guilds, Members, Channels)