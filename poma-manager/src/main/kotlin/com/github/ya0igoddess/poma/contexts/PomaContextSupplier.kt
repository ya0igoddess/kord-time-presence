package com.github.ya0igoddess.poma.contexts

import com.github.ya0igoddess.dbsync.contextual.CommandContextSupplier
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.poma.model.PomaAccount
import com.github.ya0igoddess.poma.repositories.IPomaAccountRepoService
import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.CommandContext

class PomaContextSupplier(
    private val memberRepoService: IDiscordMemberRepoService,
    private val service: IPomaAccountRepoService
): CommandContextSupplier<CommandContext, PomaManagerContext> {
    override suspend fun supply(context: CommandContext): PomaManagerContext {
        val member = context.getMember()
        member ?: throw DiscordRelayedException("Failed to find member")
        val dsMember = memberRepoService.getOrCreateFromExternal(member.asMember())
        val account = service.getById(dsMember.id) ?: throw DiscordRelayedException("Failed to load account to context")
        return SimplePomaManagerContext(account)
    }

    inner class SimplePomaManagerContext(override val account: PomaAccount): PomaManagerContext
}