package com.github.ya0igoddess.dbsync.config

import com.github.ya0igoddess.dbsync.repositories.DiscordChannelRepoService
import com.github.ya0igoddess.dbsync.repositories.DiscordGuildRepoService
import com.github.ya0igoddess.dbsync.repositories.DiscordMemberRepoService
import com.github.ya0igoddess.dbsync.repositories.DiscordUserRepoService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val repoServiceModule by DI.Module {
    importOnce(repoModule)

    bindEagerSingleton { DiscordUserRepoService(instance()) }
    bindEagerSingleton { DiscordGuildRepoService(instance()) }
    bindEagerSingleton { DiscordMemberRepoService(instance(), instance(), instance()) }
    bindEagerSingleton { DiscordChannelRepoService(instance()) }
}