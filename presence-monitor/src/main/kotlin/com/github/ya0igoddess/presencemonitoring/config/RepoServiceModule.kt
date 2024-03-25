package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import com.github.ya0igoddess.presencemonitoring.repositories.DiscordConnectionPeriodRepoService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val presenceRepoServiceModule by DI.Module {
    importAllOnce(
        presenceRepoModule,
        repoServiceModule
    )

    bindEagerSingleton { DiscordConnectionPeriodRepoService(instance()) }
}