package com.github.ya0igoddess.dbsync.extensions.dataupdates

import com.github.ya0igoddess.dbsync.extensions.DBSyncExtension
import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.dbsync.repositories.IDiscordChannelRepoService
import com.github.ya0igoddess.dbsync.repositories.IDiscordGuildRepoService
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.entity.channel.GuildChannel
import dev.kord.core.event.channel.ChannelCreateEvent
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.event.guild.MemberJoinEvent
import org.kodein.di.instance
import org.koin.core.component.inject

class DataSyncExtension: KodeinExtension() {
    override val name: String
        get() = "${DBSyncExtension.code}-update-data"

    override suspend fun setup() {
        val memberService: IDiscordMemberRepoService by di.instance()
        val guildService: IDiscordGuildRepoService by di.instance()
        val channelService: IDiscordChannelRepoService by di.instance()

        event<MemberJoinEvent> {
            action {
                memberService.createFromExternalEntity(event.member)
            }
        }

        event<GuildCreateEvent> {
            action {
                guildService.getOrCreateFromExternal(event.guild)
                event.guild.members.collect(memberService::getOrCreateFromExternal)
                event.guild.channels.collect(channelService::getOrCreateFromExternal)
            }
        }

        event<ChannelCreateEvent> {
            action {
                val channel = event.channel
                if (channel is GuildChannel) {
                    channelService.createFromExternalEntity(channel)
                }
            }
        }
    }
}