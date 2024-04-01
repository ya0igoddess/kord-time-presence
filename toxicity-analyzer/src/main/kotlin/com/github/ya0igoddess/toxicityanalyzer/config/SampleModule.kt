package com.github.ya0igoddess.toxicityanalyzer.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val ToxicityAnalyzerModule by DI.Module {
    importAllOnce(DBSyncModule, toxAnalServiceModule, toxAnalHandlerModule)
}