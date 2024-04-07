package com.github.ya0igoddess.presencemonitoring.model

import java.time.LocalDateTime

data class VoiceConnectionPeriod(
        val id: Long? = null,
        val memberId: Long,
        val channelId: Long,
        val start: LocalDateTime, //TODO: use kotlinx LocalDateTime
        val end: LocalDateTime,
)