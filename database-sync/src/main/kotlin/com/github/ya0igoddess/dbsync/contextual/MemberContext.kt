package com.github.ya0igoddess.dbsync.contextual

import com.github.ya0igoddess.dbsync.model.discord.DsMember

interface MemberContext {
    val member: DsMember?
}