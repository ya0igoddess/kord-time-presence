package com.example.commands

import com.example.model.Poma
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.EphemeralSlashCommand
import com.kotlindiscord.kord.extensions.components.buttons.EphemeralInteractionButton
import com.kotlindiscord.kord.extensions.components.buttons.EphemeralInteractionButtonContext
import com.kotlindiscord.kord.extensions.components.components
import com.kotlindiscord.kord.extensions.components.ephemeralButton
import com.kotlindiscord.kord.extensions.components.forms.ModalForm
import com.kotlindiscord.kord.extensions.types.respond
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.selects.select

class PomaSelector(
    private val label: String,
    private val pomaList: Flow<Poma>,
    val onSelect: suspend (Poma) -> Any
) {
    val builder: suspend EphemeralInteractionButton<ModalForm>.() -> Unit = {
        label = this@PomaSelector.label

        action {
            respond {
                components {
                    pomaList.collect { poma ->
                        ephemeralButton {
                            label = "${poma.name}, ${poma.level}"

                            action {
                                onSelect(poma)
                                respond {
                                    content = "Bye, ${poma.name}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}