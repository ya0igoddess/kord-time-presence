package com.github.ya0igoddess.dbsync.migration

import com.github.ya0igoddess.dbsync.config.settings.DBConnectionSettings
import liquibase.Contexts
import liquibase.Scope
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.io.Writer
import java.sql.Connection
import java.sql.DriverManager

fun loadLiquibase(settings: DBConnectionSettings, schemaCode: String, changeLogFile: String) {
    Scope.child(mapOf()) {

        //val adaptedSchemaCode = schemaCode.replace("-", "_")
        val connection: Connection? = DriverManager.getConnection(settings.url, settings.user, settings.password)
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
        connection?.prepareStatement("create schema if not exists $schemaCode")!!.execute()
        database.defaultSchemaName = schemaCode
        val liquibase = liquibase.Liquibase(changeLogFile, ClassLoaderResourceAccessor(), database)
        //liquibase.update(Contexts(), Writer.nullWriter())
        liquibase.update()
    }
}