package com.example.config

import com.example.repositories.DiscordConnectionPeriodRepoService
import com.example.repositories.ISampleRepoService
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.dsl.module

val repoServiceModule by DI.Module {
    importAll(
        repoModule,
        com.github.ya0igoddess.dbsync.config.repoServiceModule
    )

    bindSingleton { DiscordConnectionPeriodRepoService(instance()) }
}