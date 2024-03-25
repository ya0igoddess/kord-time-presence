package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import com.github.ya0igoddess.dbsync.service.settings.FileSettingsProvider
import io.r2dbc.spi.ConnectionFactory
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.bindFactory
import org.kodein.di.instance
import org.ufoss.kotysa.R2dbcSqlClient

val dataBaseModule by DI.Module {
    bindFactory<Any, KordDBSettings> { FileSettingsProvider().get() }
    bindEagerSingleton<SkaardModuleDatabase> { SkaardModuleDatabase(instance()) }
    bindEagerSingleton<ConnectionFactory> { instance<SkaardModuleDatabase>().factory }
    bindEagerSingleton<R2dbcSqlClient> { instance<SkaardModuleDatabase>().sqlClient }
}