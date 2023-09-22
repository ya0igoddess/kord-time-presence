package com.github.ya0igoddess.presencemonitoring.extension

import com.kotlindiscord.kord.extensions.extensions.Extension

class PresenceMonitorExtension: Extension() {

    companion object {
        const val code = "kord_presence_monitor"
    }

    override val name: String
        get() = code

    override suspend fun setup() {

    }

}