package com.github.ya0igoddess.presencemonitoring.service

import java.time.LocalDateTime

data class OpenedConnection(
    val userId: Long,
    val channelId: Long,
    val start: LocalDateTime
)