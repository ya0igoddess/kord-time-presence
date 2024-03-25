package com.example.config

import com.example.repositories.DiscordConnectionPeriodRepoService
import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val sampleRepoServiceModule by DI.Module {
    importAllOnce(
        sampleRepoModule,
        repoServiceModule
    )

    bindEagerSingleton { DiscordConnectionPeriodRepoService(instance()) }
}