package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.service.settings.FileSettingsProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient

val dataBaseModule = module {
    factory<KordDBSettings> { FileSettingsProvider().get() }
    single<ConnectionFactory> {
        val settings = get<KordDBSettings>().r2dbc
        val options = ConnectionFactoryOptions.builder()
            .from(ConnectionFactoryOptions.parse(settings?.url.toString()))
            .option(USER, settings?.user.toString())
            .option(PASSWORD, settings?.password.toString())
            .build()
        ConnectionFactories.get(options)
    }
    single<R2dbcSqlClient>  { get<ConnectionFactory>().coSqlClient(tables) }
}