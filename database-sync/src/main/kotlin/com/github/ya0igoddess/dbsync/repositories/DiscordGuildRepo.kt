package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Guilds
import com.github.ya0igoddess.dbsync.model.discord.DsGuild
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.ISynchronizationService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import dev.kord.core.entity.Guild
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordGuildRepoService : CRUDService<DsGuild, Long>, ISynchronizationService<DsGuild, Guild>

class DiscordGuildCRUDRepo(
        sqlClient: R2dbcSqlClient
) : KotysaLongCRUDRepository<DsGuild>(Guilds, Guilds.id, sqlClient)

class DiscordGuildRepoService(
        private val dsGuildCRUDRepo: DiscordGuildCRUDRepo
) : CRUDService<DsGuild, Long> by dsGuildCRUDRepo, IDiscordGuildRepoService {
    override suspend fun getByExternalEntity(externalEntity: Guild): DsGuild? {
        return dsGuildCRUDRepo.getById(externalEntity.id.lvalue)
    }

    override suspend fun createFromExternalEntity(externalEntity: Guild): DsGuild {
        return dsGuildCRUDRepo.save(DsGuild(
                id = externalEntity.id.lvalue,
                name = externalEntity.name
        ))
    }

    override suspend fun updateWithExternalEntity(internalEntity: DsGuild, externalEntity: Guild): DsGuild {
        return dsGuildCRUDRepo.update(internalEntity.copy(
                name = externalEntity.name
        ))
    }
}