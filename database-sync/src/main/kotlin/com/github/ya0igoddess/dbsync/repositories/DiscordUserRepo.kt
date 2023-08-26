package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Users
import com.github.ya0igoddess.dbsync.model.discord.DsUser
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.ISynchronizationService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import dev.kord.core.entity.User
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordUserRepoService : CRUDService<DsUser, Long>, ISynchronizationService<DsUser, User>
class DiscordUserCRUDRepo(
        sqlClient: R2dbcSqlClient
) : KotysaLongCRUDRepository<DsUser>(Users, Users.id, sqlClient)

class DiscordUserRepoService(
        private val discordUserCRUDRepo: DiscordUserCRUDRepo
) : CRUDService<DsUser, Long> by discordUserCRUDRepo, IDiscordUserRepoService {
    override suspend fun getByExternalEntity(externalEntity: User): DsUser? {
        return discordUserCRUDRepo.getById(externalEntity.id.lvalue)
    }

    override suspend fun createFromExternalEntity(externalEntity: User): DsUser {
        return discordUserCRUDRepo.save(DsUser(
                id = externalEntity.id.lvalue,
                name = externalEntity.username
        ))
    }

    override suspend fun updateWithExternalEntity(internalEntity: DsUser, externalEntity: User): DsUser {
        return discordUserCRUDRepo.update(internalEntity.copy(
                name = externalEntity.username
        ))
    }
}

