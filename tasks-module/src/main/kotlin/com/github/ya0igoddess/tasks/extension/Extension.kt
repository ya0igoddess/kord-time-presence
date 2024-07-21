package com.github.ya0igoddess.tasks.extension

import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.tasks.config.TaskModule
import com.github.ya0igoddess.tasks.handlers.registerShowRunningTasksCommands
import com.github.ya0igoddess.tasks.service.TaskExecutor
import org.kodein.di.instance

class TasksExtension : KodeinExtension() {

    companion object {
        const val code = "tasks_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(TaskModule)

        val taskExecutor: TaskExecutor by di.instance()
        registerShowRunningTasksCommands(taskExecutor)
    }

}