package com.github.ya0igoddess.presencemonitoring.service

import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.datetime.*
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.toPNG
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import java.io.File
import kotlin.time.Duration

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

    override suspend fun getMembersDurationStatInPeriod(channel: DsChannel, start: LocalDateTime?, end: LocalDateTime?): File {
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
        ).toList()
        val usersWithDurations = connections
                .groupBy { it.memberId }
                .mapKeys { memberService.getById(it.key)?.name } //TODO: N+1 problem
                .mapValues {
                    it.value.fold(Duration.ZERO) { duration, period ->
                        duration + (period.end.toKotlinLocalDateTime().toInstant(timeZone) -
                                period.start.toKotlinLocalDateTime().toInstant(timeZone))
                    }
                }
        val plot = plot {
            layout.title = "Total user's presence"
            barsH {
                y(usersWithDurations.keys) { axis.name = "Member" }
                x(usersWithDurations.values.map { it.inWholeHours }) {
                    axis.name = "Hours"; scale = continuous(min = 0, max = usersWithDurations.values.max().inWholeHours)
                }
                alpha = 0.75
            }
        }
        val file = File.createTempFile("skaard", ".png")
        file.writeBytes(plot.toPNG())
        return file
    }
}