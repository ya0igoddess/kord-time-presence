package com.github.ya0igoddess.tasks.service

import dev.kord.core.entity.User
import kotlinx.coroutines.Job

class CoroutineJobTask(
    private val job: Job, override val name: String, override val user: User, override val info: TaskInfo
) : Task {
    override suspend fun cancel() {
        job.cancel()
    }
}