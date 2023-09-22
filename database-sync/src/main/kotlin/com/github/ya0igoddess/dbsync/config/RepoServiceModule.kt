package com.github.ya0igoddess.dbsync.config

import com.github.ya0igoddess.dbsync.repositories.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoServiceModule = module {
    includes(repoModule)

    singleOf<IDiscordUserRepoService, DiscordUserCRUDRepo>(::DiscordUserRepoService)
    singleOf<IDiscordGuildRepoService, DiscordGuildCRUDRepo>(::DiscordGuildRepoService)
    single<IDiscordMemberRepoService> { DiscordMemberRepoService(get(), get(), get()) }
}