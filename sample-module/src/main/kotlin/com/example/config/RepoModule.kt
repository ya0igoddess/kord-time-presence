package com.example.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.example.repositories.SampleCRUDRepo
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule by DI.Module {
    importOnce(dataBaseModule)

    bindSingleton { SampleCRUDRepo(instance()) }
}