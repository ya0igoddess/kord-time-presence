package com.github.ya0igoddess.dbsync.contextual

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.SlashCommand
import com.kotlindiscord.kord.extensions.commands.application.slash.SlashCommandContext
import com.kotlindiscord.kord.extensions.components.forms.ModalForm

abstract class ContextualSlashCommand<
        C : SlashCommandContext<*, A, M>,
        A : Arguments,
        M : ModalForm,
        T: SlashCommand<C,A,M>
        >(
    private val action: suspend C.(M?) -> Unit
): suspend (T) -> Unit {
    abstract val body: suspend (T) -> Unit

    override suspend fun invoke(p1: T) {
        p1.action(action)
        body.invoke(p1)
    }
}