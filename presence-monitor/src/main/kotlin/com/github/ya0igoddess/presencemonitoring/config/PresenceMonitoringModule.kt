package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val PresenceMonitoringModule by DI.Module {
    importAllOnce(DBSyncModule, presenceServiceModule, presenceHandlerModule)
}