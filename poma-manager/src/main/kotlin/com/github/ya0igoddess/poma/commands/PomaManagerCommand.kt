package com.github.ya0igoddess.poma.commands

import com.github.ya0igoddess.poma.repositories.IPomaAccountRepoService
import com.github.ya0igoddess.poma.repositories.IPomaRepoService
import com.github.ya0igoddess.poma.service.IPomaAccountService
import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.dbsync.repositories.IDiscordMemberRepoService
import com.github.ya0igoddess.poma.contexts.PomaContextSupplier
import com.github.ya0igoddess.poma.contexts.PomaManagerContext
import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.EphemeralSlashCommand
import com.kotlindiscord.kord.extensions.components.components
import com.kotlindiscord.kord.extensions.components.ephemeralButton
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.types.respond
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class PomaManagerCommand(
    private val pomaRepoService: IPomaRepoService,
    private val pomaAccountService: IPomaAccountService,
    private val pomaContextSupplier: PomaContextSupplier
) {
    val builder: suspend EphemeralSlashCommand<Arguments, ModalForm>.() -> Unit = {
        name = "menu"
        description = "Enter Poma Manager Menu"

        action {
            val account = pomaContextSupplier.supply(this).account
            respond {
                content = "Choose action:"
                val pomas = pomaAccountService.getAccountPomas(account)
                components(30.toDuration(DurationUnit.SECONDS)) {
                    ephemeralButton { label = "Roll New Poma(\$10)"; action { val newpoma = pomaAccountService.rollNewPoma(account); respond { content = newpoma.toString() } } }
                    ephemeralButton(1, PomaSelector("Delete Poma", pomas, pomaRepoService::delete).builder)
                }
            }
        }
    }
}