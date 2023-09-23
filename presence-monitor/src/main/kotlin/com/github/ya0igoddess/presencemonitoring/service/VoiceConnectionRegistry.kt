package com.github.ya0igoddess.presencemonitoring.service

import com.github.ya0igoddess.dbsync.repositories.IDiscordChannelRepoService
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.presencemonitoring.model.VoiceConnectionPeriod
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import dev.kord.core.kordLogger
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

class VoiceConnectionRegistry(
    private val connectionRepo: IDiscordConnectionPeriodRepoService,
    private val memberRepo: IDiscordMemberRepoService,
    private val channelRepo: IDiscordChannelRepoService
) : IVoiceConnectionRegistry {
    private val openedConnections = ConcurrentHashMap<String, OpenedConnection>()

    override fun registerNewConnection(code: String, userId: Long, channelId: Long) {
        val openedConnection = OpenedConnection(userId, channelId, LocalDateTime.now())
        openedConnections[code] = openedConnection
    }

    override suspend fun closeConnection(code: String) {
        val connection = openedConnections.remove(code)
        if (connection == null) {
            kordLogger.error { "Closing nonexistent connection $code" }
            return
        }
        val endTime = LocalDateTime.now()
        val guildId = channelRepo.getById(connection.channelId)?.guildId
            ?: throw IllegalStateException("Couldn't fetch channel $connection")
        val memberId = memberRepo.getByGuildAndUser(guildId, connection.userId)?.id
            ?: throw IllegalStateException("Couldn't fetch member $connection")
        connectionRepo.save(VoiceConnectionPeriod(
            memberId = memberId,
            channelId = connection.channelId,
            start = connection.start,
            end = endTime
        ))
    }

    override val currentConnections: Map<String, OpenedConnection>
        get() = openedConnections
}