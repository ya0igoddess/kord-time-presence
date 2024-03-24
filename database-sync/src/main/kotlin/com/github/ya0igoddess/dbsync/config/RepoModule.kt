package com.github.ya0igoddess.dbsync.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.github.ya0igoddess.dbsync.repositories.DiscordChannelCRUDRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordGuildCRUDRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordMemberCrudRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordUserCRUDRepo
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val repoModule by DI.Module {
    importOnce(dataBaseModule)

    bindSingleton { DiscordGuildCRUDRepo(instance()) }
    bindSingleton { DiscordUserCRUDRepo(instance()) }
    bindSingleton { DiscordMemberCrudRepo(instance()) }
    bindSingleton { DiscordChannelCRUDRepo(instance()) }
}