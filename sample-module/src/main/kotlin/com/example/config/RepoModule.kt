package com.example.config

import com.example.repositories.SampleCRUDRepo
import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val sampleRepoModule by DI.Module {
    importOnce(dataBaseModule)

    bindEagerSingleton { SampleCRUDRepo(instance()) }
}