package com.github.ya0igoddess.dbsync.database

import com.github.ya0igoddess.dbsync.config.settings.DBConnectionSettings
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient
import org.ufoss.kotysa.PostgresqlTables
import org.ufoss.kotysa.postgresql.IPostgresqlTable
import org.ufoss.kotysa.r2dbc.coSqlClient
import org.ufoss.kotysa.tables


class SkaardModuleDatabase(
    private val initialSettings: KordDBSettings
) {
    fun addSchema(schemaCode: String) {
    }

    private val tablesList = mutableListOf<IPostgresqlTable<*>>(FakeTable)
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

    private fun cratePostgresTables(): PostgresqlTables {
        val tablesArray = tablesList.toTypedArray()
        return tables().postgresql(*tablesArray)
    }

    private val updatableSqlClient = UpdatableSqlClient(factory.coSqlClient(cratePostgresTables()))
    val sqlClient: PostgresqlR2dbcSqlClient
        get() = updatableSqlClient
    fun addTables(tables: List<IPostgresqlTable<*>>) {
        tablesList.addAll(tables)
        updatableSqlClient.r2dbcSqlClient = factory.coSqlClient(cratePostgresTables())
    }
}
