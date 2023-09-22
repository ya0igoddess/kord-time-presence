package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.repositories.DiscordConnectionPeriodRepoService
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoServiceModule = module {
    includes(repoModule)

    single<IDiscordConnectionPeriodRepoService> { DiscordConnectionPeriodRepoService(get()) }
}