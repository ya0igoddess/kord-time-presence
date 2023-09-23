package com.github.ya0igoddess.presencemonitoring.handlers

import com.github.ya0igoddess.dbsync.model.discord.lvalue
import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionRegistry
import dev.kord.core.event.user.VoiceStateUpdateEvent

class VoiceStatusChangeHandler(
    private val registry: IVoiceConnectionRegistry
) {
    suspend fun handle(event: VoiceStateUpdateEvent) {
        val oldState = event.old
        val newState = event.state
        if (oldState?.channelId == newState.channelId) return //interested only in channel moving
        oldState?.let { registry.closeConnection(it.sessionId) }
        val newChannel = newState.channelId
        if (newChannel == null ) return
        registry.registerNewConnection(newState.sessionId, newState.userId.lvalue, newChannel.lvalue)
    }
}