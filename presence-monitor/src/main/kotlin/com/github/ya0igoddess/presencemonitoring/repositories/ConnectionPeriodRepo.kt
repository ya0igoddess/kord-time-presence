package com.github.ya0igoddess.presencemonitoring.repositories

import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.service.common.CRUDService
import com.github.ya0igoddess.dbsync.service.common.KotysaLongCRUDRepository
import com.github.ya0igoddess.presencemonitoring.database.ConnectionPeriods
import com.github.ya0igoddess.presencemonitoring.model.VoiceConnectionPeriod
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.ufoss.kotysa.R2dbcSqlClient


interface IDiscordConnectionPeriodRepoService : CRUDService<VoiceConnectionPeriod, Long> {
    fun getConnectionsInPeriod(channel: DsChannel, start: LocalDateTime, end: LocalDateTime): Flow<VoiceConnectionPeriod>
}

class VoiceConnectionPeriodCRUDRepo(
    sqlClient: R2dbcSqlClient
): KotysaLongCRUDRepository<VoiceConnectionPeriod>(ConnectionPeriods, ConnectionPeriods.id, sqlClient)

class DiscordConnectionPeriodRepoService(
        private val connectionCRUDRepo: VoiceConnectionPeriodCRUDRepo,
        private val sqlClient: R2dbcSqlClient,
) : CRUDService<VoiceConnectionPeriod, Long> by connectionCRUDRepo, IDiscordConnectionPeriodRepoService {
    override fun getConnectionsInPeriod(channel: DsChannel, start: LocalDateTime, end: LocalDateTime): Flow<VoiceConnectionPeriod> {
        return (sqlClient
                select ConnectionPeriods from ConnectionPeriods
                where ConnectionPeriods.channelId eq channel.id
                and ConnectionPeriods.start afterOrEq start.toJavaLocalDateTime()
                and ConnectionPeriods.end beforeOrEq end.toJavaLocalDateTime()
                orderByAsc ConnectionPeriods.start
                ).fetchAll()
    }
}