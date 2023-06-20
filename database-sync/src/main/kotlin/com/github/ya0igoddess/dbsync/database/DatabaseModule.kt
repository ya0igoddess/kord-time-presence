package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.service.settings.FileSettingService
import com.github.ya0igoddess.dbsync.service.settings.SettingsService
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient

val dataBaseModule = module {
    singleOf<SettingsService>(::FileSettingService)
    single<ConnectionFactory> { ConnectionFactories.get(get<SettingsService>().getValue<String>("r2dbc.url")) }
    single<R2dbcSqlClient>  { get<ConnectionFactory>().coSqlClient(tables) }
}