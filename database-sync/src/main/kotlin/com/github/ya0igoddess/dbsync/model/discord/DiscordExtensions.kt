package com.github.ya0igoddess.dbsync.model.discord

import dev.kord.common.entity.Snowflake

 val Snowflake.lvalue: Long
     get() = this.value.toLong()