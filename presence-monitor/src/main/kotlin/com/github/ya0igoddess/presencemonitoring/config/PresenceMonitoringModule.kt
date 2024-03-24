package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import org.kodein.di.DI
import org.koin.dsl.module

val PresenceMonitoringModule = DI.Module {
    importAll(DBSyncModule, serviceModule, handlerModule)
}