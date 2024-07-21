package com.github.ya0igoddess.tasks.extension

import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.tasks.config.TaskModule
import com.kotlindiscord.kord.extensions.commands.events.ChatCommandInvocationEvent
import com.kotlindiscord.kord.extensions.extensions.event

class TasksExtension : KodeinExtension() {

    companion object {
        const val code = "tasks_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(TaskModule)


        event<ChatCommandInvocationEvent> {
        }
    }

}