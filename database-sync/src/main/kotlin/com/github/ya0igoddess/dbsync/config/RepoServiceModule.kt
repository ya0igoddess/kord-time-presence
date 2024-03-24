package com.github.ya0igoddess.dbsync.config

import com.github.ya0igoddess.dbsync.repositories.*
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoServiceModule by DI.Module {
    importOnce(repoModule)

    bindSingleton { DiscordUserRepoService(instance()) }
    bindSingleton{ DiscordGuildRepoService(instance()) }
    bindSingleton { DiscordMemberRepoService(instance(), instance(), instance()) }
    bindSingleton {DiscordChannelRepoService(instance()) }
}