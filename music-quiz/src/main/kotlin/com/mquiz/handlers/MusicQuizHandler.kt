package com.mquiz.handlers

import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.mquiz.service.ISampleService

class MusicQuizHandler(
        private val service: ISampleService
) {
    suspend fun handle(event: ChatCommandInvocationEvent) {
        service.doSomething()
    }
}