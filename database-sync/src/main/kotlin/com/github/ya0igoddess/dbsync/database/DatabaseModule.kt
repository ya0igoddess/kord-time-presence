package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.service.settings.FileSettingsProvider
import io.r2dbc.spi.*
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.koin.dsl.module
import org.reactivestreams.Publisher
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient

val dataBaseModule = module {
    factory<KordDBSettings> { FileSettingsProvider().get() }
    single<SkaardModuleDatabase> { SkaardModuleDatabase(get()) }
    single<ConnectionFactory> { get<SkaardModuleDatabase>().factory }
    single<R2dbcSqlClient>  { get<SkaardModuleDatabase>().sqlClient }
}