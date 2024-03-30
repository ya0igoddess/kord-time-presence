package com.github.ya0igoddess.presencemonitoring.handlers

import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.repositories.IDiscordChannelRepoService
import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionUserService
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.PublicSlashCommand
import com.kotlindiscord.kord.extensions.commands.converters.impl.channel
import com.kotlindiscord.kord.extensions.commands.converters.impl.string
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetUniqueUsersByPeriodArgs : Arguments() {
    val channel by channel { name = "channel"; description = "Channel to get info" }
    val start by string { name = "period_end"; description = "Period start" }
    val end by string { name = "period_start"; description = "Period end" }
}


internal suspend fun Extension.RegisterGetUniqueUsersByPeriodCommand(
        service: IVoiceConnectionUserService,
        channelRepo: IDiscordChannelRepoService
): PublicSlashCommand<GetUniqueUsersByPeriodArgs, ModalForm> {
    return publicSlashCommand(::GetUniqueUsersByPeriodArgs) {
        name = "get_unique_users"
        description = "Get list of active users in given period"
        action {
            val timeZone = TimeZone.currentSystemDefault()
            val start = Instant.parse(arguments.start).toLocalDateTime(timeZone)
            val end = Instant.parse(arguments.end).toLocalDateTime(timeZone)
            val channel = requireNotNull(channelRepo.getById(arguments.channel.id.lvalue))
            respond {
                content = service.getUniqueMembersByPeriod(channel, start, end).map { it.name }.toString()
            }
        }
    }
}