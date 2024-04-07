package com.github.ya0igoddess.presencemonitoring.handlers

import com.github.ya0igoddess.dbsync.converters.dateOrDateTime
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.repositories.IDiscordChannelRepoService
import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionUserService
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.PublicSlashCommand
import com.kotlindiscord.kord.extensions.commands.converters.impl.channel
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import dev.kord.rest.builder.message.addFile

class GetUniqueUsersByPeriodArgs : Arguments() {
    val channel by channel { name = "channel"; description = "Channel to get info" }
    val start by dateOrDateTime { name = "start"; description = "Period start" }
    val end by dateOrDateTime { name = "end"; description = "Period end" }
}


internal suspend fun Extension.RegisterGetUniqueUsersByPeriodCommand(
        service: IVoiceConnectionUserService,
        channelRepo: IDiscordChannelRepoService
): PublicSlashCommand<GetUniqueUsersByPeriodArgs, ModalForm> {
    return publicSlashCommand(::GetUniqueUsersByPeriodArgs) {
        name = "get_unique_users"
        description = "Get list of active users in given period"
        action {
            val start = arguments.start
            val end = arguments.end
            val channel = requireNotNull(channelRepo.getById(arguments.channel.id.lvalue))
            respond {
                content = service.getUniqueMembersByPeriod(channel, start, end).map { it.name }.toString()
            }
        }
    }
}

internal suspend fun Extension.RegisterGetTotalActivityByPeriod(
        service: IVoiceConnectionUserService,
        channelRepo: IDiscordChannelRepoService
): PublicSlashCommand<GetUniqueUsersByPeriodArgs, ModalForm> {
    return publicSlashCommand(::GetUniqueUsersByPeriodArgs) {
        name = "get_unique_users_info"
        description = "Get list of active users in given period"
        action {
            val start = arguments.start
            val end = arguments.end
            val channel = requireNotNull(channelRepo.getById(arguments.channel.id.lvalue))
            respond {
                content = ""
                addFile(service.getMembersDurationStatInPeriod(channel, start, end).toPath())
            }
        }
    }
}