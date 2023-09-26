package com.github.ya0igoddess.poma.commands

import com.github.ya0igoddess.dbsync.contextual.CommandContextSupplier
import com.github.ya0igoddess.dbsync.contextual.MemberContext
import com.github.ya0igoddess.dbsync.contextual.member.MemberContextSupplier
import com.github.ya0igoddess.poma.service.IPomaAccountService
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.CommandContext
import com.kotlindiscord.kord.extensions.commands.application.slash.EphemeralSlashCommand
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.types.respond

class CreatePomaAccountCommand(
    private val pomaAccountService: IPomaAccountService,
    private val memberContext: MemberContextSupplier
) {
    val builder: suspend EphemeralSlashCommand<Arguments, ModalForm>.() -> Unit = {
        name = "createPomaAccount"
        description = "Creates new Poma Manager Account"

        action {
            val member = memberContext.supply(this).member
                ?: throw DiscordRelayedException("User not found")
            pomaAccountService.createPomaAccount(member)
            respond { content = "Your Poma Manager account was created successfully." }
        }
    }
}