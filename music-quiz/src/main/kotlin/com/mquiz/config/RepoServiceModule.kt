package com.mquiz.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import com.mquiz.repositories.DiscordConnectionPeriodRepoService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val musicQuizRepoServiceModule by DI.Module {
    importAllOnce(
            musicQuizRepoModule,
            repoServiceModule
    )

    bindEagerSingleton { DiscordConnectionPeriodRepoService(instance()) }
}