package com.github.ya0igoddess.poma.contexts

import com.github.ya0igoddess.dbsync.contextual.CommandContextSupplier
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.poma.model.PomaAccount
import com.github.ya0igoddess.poma.repositories.IPomaAccountRepoService
import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.CommandContext

class PomaContextSupplier(
    private val service: IPomaAccountRepoService
): CommandContextSupplier<CommandContext, PomaManagerContext> {
    override suspend fun supply(context: CommandContext): PomaManagerContext {
        val member = context.getUser()
        member ?: throw DiscordRelayedException("Failed to find member")
        val id = member.id.lvalue
        val account = service.getById(id) ?: throw DiscordRelayedException("Failed to load account to context")
        return SimplePomaManagerContext(account)
    }

    inner class SimplePomaManagerContext(override val account: PomaAccount): PomaManagerContext
}