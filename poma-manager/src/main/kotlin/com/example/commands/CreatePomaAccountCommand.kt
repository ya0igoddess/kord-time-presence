package com.example.commands

import com.example.service.IPomaAccountService
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.EphemeralSlashCommand
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.types.respond

class CreatePomaAccountCommand(
    private val memberRepoService: IDiscordMemberRepoService,
    private val pomaAccountService: IPomaAccountService
) {
    val builder: suspend EphemeralSlashCommand<Arguments, ModalForm>.() -> Unit = {
        name = "createPomaAccount"
        description = "Creates new Poma Manager Account"

        action {
            val guild = event.interaction.invokedCommandGuildId
            val dsMember = event.interaction.user.asMember(guild!!)
            val member = memberRepoService.getOrCreateFromExternal(dsMember)
            pomaAccountService.createPomaAccount(member)
            respond { content = "Your Poma Manager account was created successfully." }
        }
    }
}