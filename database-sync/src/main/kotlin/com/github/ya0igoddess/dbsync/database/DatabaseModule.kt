package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.service.settings.FileSettingsProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient

val dataBaseModule = module {
    factory<KordDBSettings> { FileSettingsProvider().get() }
    single<ConnectionFactory> { ConnectionFactories.get(get<KordDBSettings>().r2dbc.url) }
    single<R2dbcSqlClient>  { get<ConnectionFactory>().coSqlClient(tables) }
}