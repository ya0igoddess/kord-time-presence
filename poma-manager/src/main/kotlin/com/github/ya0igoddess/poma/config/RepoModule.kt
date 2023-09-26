package com.github.ya0igoddess.poma.config

import com.github.ya0igoddess.poma.repositories.PomaAccountCRUDRepo
import com.github.ya0igoddess.poma.repositories.PomaCRUDRepo
import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ufoss.kotysa.R2dbcSqlClient

val repoModule = module {
    includes(dataBaseModule)

    singleOf<PomaAccountCRUDRepo, R2dbcSqlClient>(::PomaAccountCRUDRepo)
    singleOf<PomaCRUDRepo, R2dbcSqlClient>(::PomaCRUDRepo)
}