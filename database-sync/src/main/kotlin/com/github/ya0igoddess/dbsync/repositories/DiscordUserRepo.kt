package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Users
import com.github.ya0igoddess.dbsync.model.discord.DiscordUser
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordUserRepoService : CRUDService<DiscordUser, Long>
class DiscordUserCRUDRepo(
        sqlClient: R2dbcSqlClient
) : KotysaLongCRUDRepository<DiscordUser>(Users, Users.id, sqlClient), IDiscordUserRepoService

class DiscordUserRepoService(
        discordUserCRUDRepo: DiscordUserCRUDRepo
) : CRUDService<DiscordUser, Long> by discordUserCRUDRepo, IDiscordUserRepoService

