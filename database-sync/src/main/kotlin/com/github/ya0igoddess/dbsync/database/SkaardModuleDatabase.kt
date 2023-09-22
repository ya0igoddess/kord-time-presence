package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.DBConnectionSettings
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions


class SkaardModuleDatabase(
    private val initialSettings: KordDBSettings
) {
    fun addSchema(schemaCode: String) {

    }

    private var settings = initialSettings
        set(value) { updatableConnectionFactory.factory = createFactoryFromSettings(value.r2dbc) }


    private val updatableConnectionFactory = UpdatableConnectionFactory(createFactoryFromSettings(settings.r2dbc))

    val factory: ConnectionFactory = updatableConnectionFactory
    private fun createFactoryFromSettings(dbSettings: DBConnectionSettings?): ConnectionFactory {
        val options = ConnectionFactoryOptions.builder()
            .from(ConnectionFactoryOptions.parse(dbSettings?.url.toString()))
            .option(ConnectionFactoryOptions.USER, dbSettings?.user.toString())
            .option(ConnectionFactoryOptions.PASSWORD, dbSettings?.password.toString())
            .build()
        return ConnectionFactories.get(options)
    }
}