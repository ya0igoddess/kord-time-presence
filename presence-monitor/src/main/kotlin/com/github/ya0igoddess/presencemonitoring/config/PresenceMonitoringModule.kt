package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import org.koin.dsl.module

val PresenceMonitoringModule = module {
    includes(DBSyncModule, serviceModule, handlerModule)
}