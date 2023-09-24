package com.example.config

import com.example.repositories.PomaAccountCRUDRepo
import com.example.repositories.PomaCRUDRepo
import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule = module {
    includes(dataBaseModule)

    singleOf<PomaAccountCRUDRepo, R2dbcSqlClient>(::PomaAccountCRUDRepo)
    singleOf<PomaCRUDRepo, R2dbcSqlClient>(::PomaCRUDRepo)
}