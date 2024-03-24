package com.github.ya0igoddess.dbsync.model.discord

data class DsMember(
    val id: Long = 0L,
    val name: String,
    val guildId: Long,
    val userId: Long
)