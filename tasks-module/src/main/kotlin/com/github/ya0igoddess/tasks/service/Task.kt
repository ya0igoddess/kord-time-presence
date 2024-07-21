package com.github.ya0igoddess.tasks.service

import dev.kord.core.entity.User

interface Task {
    val name: String
    val user: User
    val info: TaskInfo
    suspend fun cancel()
}