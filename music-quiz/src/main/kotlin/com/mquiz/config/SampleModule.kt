package com.mquiz.config

import com.github.ya0igoddess.dbsync.config.DBSyncModule
import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val MusicQuizModule by DI.Module {
    importAllOnce(DBSyncModule, musicQuizServiceModule, musicQuizHandlerModule)
}