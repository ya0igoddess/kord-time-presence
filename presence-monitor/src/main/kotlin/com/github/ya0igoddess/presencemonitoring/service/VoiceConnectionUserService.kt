package com.github.ya0igoddess.presencemonitoring.service

import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.datetime.*

class VoiceConnectionUserService(
        private val connectionRepository: IDiscordConnectionPeriodRepoService,
        private val memberService: IDiscordMemberRepoService
) : IVoiceConnectionUserService {
    override suspend fun getUniqueMembersByPeriod(channel: DsChannel, start: LocalDateTime?, end: LocalDateTime?): List<DsMember> {
        val timeZone = TimeZone.currentSystemDefault()
        val actualStart = start ?: LocalDate
                .fromEpochDays(0)
                .atStartOfDayIn(timeZone)
                .toLocalDateTime(timeZone)
        val actualEnd = Clock.System.now().toLocalDateTime(timeZone)
        val connections = connectionRepository.getConnectionsInPeriod(
                channel,
                actualStart,
                actualEnd
        )
        val membersIds = connections.map { it.memberId }.toList().distinct()
        val members = memberService.getAllByIds(membersIds)
        return members.toList()
    }
}