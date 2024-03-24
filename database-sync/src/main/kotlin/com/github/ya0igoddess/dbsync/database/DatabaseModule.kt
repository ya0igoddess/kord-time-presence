package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.service.settings.FileSettingsProvider
import io.r2dbc.spi.*
import org.kodein.di.*
import org.ufoss.kotysa.R2dbcSqlClient

val dataBaseModule by DI.Module {
    bindFactory<Any, KordDBSettings> { FileSettingsProvider().get() }
    bindSingleton<SkaardModuleDatabase> { SkaardModuleDatabase(instance()) }
    bindSingleton<ConnectionFactory> { instance<SkaardModuleDatabase>().factory }
    bindSingleton<R2dbcSqlClient>  { instance<SkaardModuleDatabase>().sqlClient }
}