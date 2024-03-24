package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.repositories.DiscordConnectionPeriodRepoService
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoServiceModule = DI.Module {
    importAll(
        repoModule,
        com.github.ya0igoddess.dbsync.config.repoServiceModule
    )

    bindSingleton { DiscordConnectionPeriodRepoService(instance()) }
}