package com.github.ya0igoddess.presencemonitoring.service

interface IVoiceConnectionRegistry {
    fun registerNewConnection(code: String, userId: Long, channelId: Long)

    suspend fun closeConnection(code: String)

    val currentConnections: Map<String, OpenedConnection>
}

