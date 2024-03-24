package com.github.ya0igoddess.dbsync.config

import org.kodein.di.DI

val DBSyncModule by DI.Module {
    importOnce(repoServiceModule)
}

