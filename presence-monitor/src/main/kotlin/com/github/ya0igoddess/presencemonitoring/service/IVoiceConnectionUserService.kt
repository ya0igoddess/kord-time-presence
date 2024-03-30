package com.github.ya0igoddess.presencemonitoring.service

import com.github.ya0igoddess.dbsync.model.discord.DsChannel
import com.github.ya0igoddess.dbsync.model.discord.DsMember
import kotlinx.datetime.LocalDateTime

interface IVoiceConnectionUserService {
    suspend fun getUniqueMembersByPeriod(channel: DsChannel, start: LocalDateTime?, end: LocalDateTime?): List<DsMember>
}