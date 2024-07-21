package com.github.ya0igoddess.tasks.service

import dev.kord.core.entity.User
import kotlinx.coroutines.CoroutineScope

interface TaskExecutor {
    fun getRunningTasks(): List<Task>
    fun submit(name: String, creator: User, task: suspend CoroutineScope.(TaskInfo) -> Unit): Task
}