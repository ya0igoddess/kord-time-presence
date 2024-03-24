package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.github.ya0igoddess.dbsync.repositories.DiscordChannelCRUDRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordGuildCRUDRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordMemberCrudRepo
import com.github.ya0igoddess.dbsync.repositories.DiscordUserCRUDRepo
import com.github.ya0igoddess.presencemonitoring.model.VoiceConnectionPeriod
import com.github.ya0igoddess.presencemonitoring.repositories.IDiscordConnectionPeriodRepoService
import com.github.ya0igoddess.presencemonitoring.repositories.VoiceConnectionPeriodCRUDRepo
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule = DI.Module {
    importOnce(dataBaseModule)

    bindSingleton{ VoiceConnectionPeriodCRUDRepo(instance()) }
}