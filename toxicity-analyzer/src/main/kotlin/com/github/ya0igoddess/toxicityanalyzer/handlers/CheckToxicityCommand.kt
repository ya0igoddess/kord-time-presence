package com.github.ya0igoddess.toxicityanalyzer.handlers

import com.github.ya0igoddess.toxicityanalyzer.service.IToxicityCheck
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.ephemeralMessageCommand
import dev.kord.core.entity.ReactionEmoji

internal suspend fun Extension.registerCheckToxicityCommand(
        service: IToxicityCheck
) {
    ephemeralMessageCommand {
        name = "Check toxicity"

        action {
            val target = targetMessages.first()
            val checkResult = service.checkIfMessageToxic(target.content)

            if (checkResult.toxic) {
                target.addReaction(ReactionEmoji.Unicode("\u2623\uFE0F"))
            } else {
                target.addReaction(ReactionEmoji.Unicode("\uD83D\uDC7C"))
            }

            respond { content = checkResult.toString() }.delete()
        }
    }
}