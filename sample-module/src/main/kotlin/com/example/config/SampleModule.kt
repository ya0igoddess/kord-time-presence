package com.example.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import org.kodein.di.DI
import org.koin.dsl.module

val SampleModule = DI.Module {
    importAll(DBSyncModule, serviceModule, handlerModule)
}