package com.mquiz.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.mquiz.repositories.SampleCRUDRepo
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val musicQuizRepoModule by DI.Module {
    importOnce(dataBaseModule)

    bindEagerSingleton { SampleCRUDRepo(instance()) }
}