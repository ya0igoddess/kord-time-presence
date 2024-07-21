package com.github.ya0igoddess.tasks.handlers

import com.github.ya0igoddess.tasks.service.TaskExecutor
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.group
import com.kotlindiscord.kord.extensions.commands.application.slash.publicSubCommand
import com.kotlindiscord.kord.extensions.commands.converters.impl.long
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import kotlinx.coroutines.delay

internal suspend fun Extension.registerShowRunningTasksCommands(executorService: TaskExecutor) {
    publicSlashCommand {
        name = "tasks"
        description = "Tasks info"

        group("debug") {
            description = "Tasks debugging commands"

            publicSubCommand {
                name = "list_tasks"
                description = "Lists currently running tasks"

                action {
                    respond { content = executorService.getRunningTasks().toString() }
                }
            }

            publicSubCommand(::DummyTaskArguments) {
                name = "create_dummy_task"
                description = "Creates a new task"

                action {
                    val delayMills = arguments.delay
                    val task = executorService.submit("Dummy task $delayMills", user.asUser()) {
                        delay(delayMills)
                    }
                    respond { content = "Task ${task.name} created" }
                }
            }
        }
    }
}

class DummyTaskArguments : Arguments() {
    val delay by long { name = "delay"; description = "Task execution delay in mills" }
}