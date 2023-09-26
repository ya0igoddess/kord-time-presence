package com.github.ya0igoddess.dbsync.contextual

import com.kotlindiscord.kord.extensions.commands.CommandContext

interface CommandContextSupplier<in T: CommandContext, out O> {
    suspend fun supply(context: T): O
}