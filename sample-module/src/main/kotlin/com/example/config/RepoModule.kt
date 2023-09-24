package com.example.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.example.repositories.SampleCRUDRepo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule = module {
    includes(dataBaseModule)

    singleOf<SampleCRUDRepo, R2dbcSqlClient>(::SampleCRUDRepo)
}