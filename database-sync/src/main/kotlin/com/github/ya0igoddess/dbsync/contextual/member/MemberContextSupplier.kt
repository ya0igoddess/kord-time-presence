package com.github.ya0igoddess.dbsync.contextual.member

import com.github.ya0igoddess.dbsync.contextual.CommandContextSupplier
import com.github.ya0igoddess.dbsync.contextual.MemberContext
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.kotlindiscord.kord.extensions.commands.CommandContext
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class MemberContextSupplier(
    private val memberRepoService: IDiscordMemberRepoService
): CommandContextSupplier<CommandContext, MemberContext> {
    override suspend fun supply(context: CommandContext): MemberContext {
        val member = context.getMember()?.asMember()
            ?: throw IllegalArgumentException("Failed to fetch member context in command ${context.commandName}")
        val dsMember = member.let { memberRepoService.getByExternalEntity(it) }
            ?: throw IllegalStateException("Failed to fetch member $member")
        return SimpleMemberContext(dsMember)
    }

    inner class SimpleMemberContext(override val member: DsMember) : MemberContext
}