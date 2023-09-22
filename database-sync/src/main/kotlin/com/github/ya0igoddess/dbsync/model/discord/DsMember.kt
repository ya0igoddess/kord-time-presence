package com.github.ya0igoddess.dbsync.model.discord

data class AbstractDsMember(
    val id: Long? = 0L,
    val name: String,
    val guildId: Long,
    val userId: Long
) {
    constructor(dsMember: DsMember) : this(null, dsMember.name, dsMember.guildId, dsMember.userId)

    val asMember = DsMember(id ?: 0, name, guildId, userId)
}

data class DsMember(
    val id: Long = 0L,
    val name: String,
    val guildId: Long,
    val userId: Long
)