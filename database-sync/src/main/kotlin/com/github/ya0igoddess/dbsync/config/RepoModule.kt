package com.github.ya0igoddess.dbsync.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.github.ya0igoddess.dbsync.repositories.DiscordGuildCRUDRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordUserCRUDRepo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule = module {
    includes(dataBaseModule)

    singleOf<DiscordGuildCRUDRepo, R2dbcSqlClient>(::DiscordGuildCRUDRepo)
    singleOf<DiscordUserCRUDRepo, R2dbcSqlClient>(::DiscordUserCRUDRepo)
}