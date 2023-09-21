package com.github.ya0igoddess.dbsync.migration

import com.github.ya0igoddess.dbsync.config.settings.DBConnectionSettings
import liquibase.Scope
import liquibase.database.DatabaseFactory
import liquibase.database.ObjectQuotingStrategy
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.Connection
import java.sql.DriverManager

fun loadLiquibase(settings: DBConnectionSettings, schemaCode: String, changeLogFile: String) {
    Scope.child(mapOf()) {

        val fixedSchemaCode = schemaCode.replace("-","_") //liquibase cant handle quotes correctly
        val connection: Connection = DriverManager.getConnection(settings.url, settings.user, settings.password)
        connection.prepareStatement("create schema if not exists $fixedSchemaCode").execute()
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
        database.objectQuotingStrategy = ObjectQuotingStrategy.QUOTE_ALL_OBJECTS
        database.defaultSchemaName = fixedSchemaCode
        val liquibase = liquibase.Liquibase(changeLogFile, ClassLoaderResourceAccessor(), database)
        liquibase.update()
    }
}