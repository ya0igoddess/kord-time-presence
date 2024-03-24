package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import org.kodein.di.DI

val PresenceMonitoringModule by DI.Module {
    importAll(DBSyncModule, serviceModule, handlerModule)
}