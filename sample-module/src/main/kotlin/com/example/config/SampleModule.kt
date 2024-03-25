package com.example.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val SampleModule by DI.Module {
    importAllOnce(DBSyncModule, sampleServiceModule, sampleHandlerModule)
}