package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Channels
import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.ISynchronizationService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import dev.kord.core.entity.channel.GuildChannel
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordChannelRepoService : CRUDService<DsChannel, Long>, ISynchronizationService<DsChannel, GuildChannel>

class DiscordChannelCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<DsChannel>(Channels, Channels.id, sqlClient)

class DiscordChannelRepoService(
    private val dsChannelCRUDRepo: DiscordChannelCRUDRepo
) : CRUDService<DsChannel, Long> by dsChannelCRUDRepo, IDiscordChannelRepoService {
    override suspend fun getByExternalEntity(externalEntity: GuildChannel): DsChannel? {
        return dsChannelCRUDRepo.getById(externalEntity.id.lvalue)
    }

    override suspend fun createFromExternalEntity(externalEntity: GuildChannel): DsChannel {
        return dsChannelCRUDRepo.save(
            DsChannel(
            id = externalEntity.id.lvalue,
            name = externalEntity.name,
            guildId = externalEntity.guildId.lvalue
        ))
    }

    override suspend fun updateWithExternalEntity(internalEntity: DsChannel, externalEntity: GuildChannel): DsChannel {
        TODO("Not yet implemented")
    }
}