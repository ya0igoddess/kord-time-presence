package com.example.config

import com.example.repositories.DiscordConnectionPeriodRepoService
import com.example.repositories.ISampleRepoService
import org.koin.dsl.module

val repoServiceModule = module {
    includes(
        repoModule,
        com.github.ya0igoddess.dbsync.config.repoServiceModule
    )

    single<ISampleRepoService> { DiscordConnectionPeriodRepoService(get()) }
}