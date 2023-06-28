package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Guilds
import com.github.ya0igoddess.dbsync.database.Users
import com.github.ya0igoddess.dbsync.model.discord.DiscordGuild
import com.github.ya0igoddess.dbsync.model.discord.DiscordUser
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordGuildRepoService : CRUDService<DiscordGuild, Long>

class DiscordGuildCRUDRepo(
        sqlClient: R2dbcSqlClient
) : KotysaLongCRUDRepository<DiscordGuild>(Guilds, Guilds.id, sqlClient)

class DiscordGuildRepoService(
        discoGuildCRUDRepo: DiscordGuildCRUDRepo
) : CRUDService<DiscordGuild, Long> by discoGuildCRUDRepo, IDiscordGuildRepoService