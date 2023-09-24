package com.example.handlers

import com.example.service.ISampleService
import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
class SampleHandler(
    private val service: ISampleService
) {
    suspend fun handle(event: ChatCommandInvocationEvent) {
        service.doSomething()
    }
}