package com.github.ya0igoddess.tasks.service

import dev.kord.core.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.*

class DefaultTaskExecutor : TaskExecutor {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val tasks: MutableList<Task> = Collections.synchronizedList(mutableListOf())

    override fun submit(name: String, creator: User, task: suspend CoroutineScope.(TaskInfo) -> Unit): Task {
        val taskInfo = TaskInfo()
        val job = coroutineScope.launch {
            task.invoke(coroutineScope, taskInfo)
        }
        val createdTask = CoroutineJobTask(job, name, creator, taskInfo)
        tasks.add(createdTask)
        job.invokeOnCompletion { cause ->
            tasks.remove(createdTask)
            if (cause != null) {
                logger.error("Submitted task failed", cause)
            }
        }
        return createdTask
    }

    override fun getRunningTasks(): List<Task> {
        return tasks
    }
}