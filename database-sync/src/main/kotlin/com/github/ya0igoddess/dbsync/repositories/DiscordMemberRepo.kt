package com.github.ya0igoddess.dbsync.repositories

import com.github.ya0igoddess.dbsync.database.Members
import com.github.ya0igoddess.dbsync.model.discord.DsGuild
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.model.discord.DsUser
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.ISynchronizationService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import dev.kord.core.entity.Member
import org.ufoss.kotysa.R2dbcSqlClient

interface IDiscordMemberRepoService : CRUDService<DsMember, Long>, ISynchronizationService<DsMember, Member> {
    suspend fun getByGuildAndUser(guild: DsGuild, user: DsUser): DsMember? = getByGuildAndUser(guild.id.toLong(), user.id.toLong())
    suspend fun getByGuildAndUser(guildId: Long, userId: Long): DsMember?
}

class DiscordMemberCrudRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<DsMember>(Members, Members.id, sqlClient) {
    suspend fun getByGuildAndUser(guildId: Long, userId: Long): DsMember? {
        return (sqlClient
                select Members
                from Members
                where
                    Members.guildId eq guildId and
                    Members.userId eq userId
                ).fetchFirst()
    }
}

class DiscordMemberRepoService(
    private val userService: IDiscordUserRepoService,
    private val guildService: IDiscordGuildRepoService,
    private val repo: DiscordMemberCrudRepo
): IDiscordMemberRepoService, CRUDService<DsMember, Long> by repo {
    override suspend fun getByGuildAndUser(guildId: Long, userId: Long): DsMember? {
        return repo.getByGuildAndUser(guildId, userId)
    }

    override suspend fun getByExternalEntity(externalEntity: Member): DsMember? {
        return getByGuildAndUser(externalEntity.guildId.value.toLong(), externalEntity.asUser().id.value.toLong())
    }

    override suspend fun createFromExternalEntity(externalEntity: Member): DsMember {
        return repo.save(externalEntity.let { DsMember(
            name = it.nickname ?: it.username,
            guildId = it.guildId.lvalue,
            userId = it.asUser().id.lvalue
        ) })
    }

    override suspend fun updateWithExternalEntity(internalEntity: DsMember, externalEntity: Member): DsMember {
        return repo.update(internalEntity.copy(
            name = externalEntity.nickname ?: externalEntity.username
        ))
    }
}


