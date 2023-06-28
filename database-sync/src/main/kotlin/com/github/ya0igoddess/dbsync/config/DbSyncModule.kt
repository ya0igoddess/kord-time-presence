package com.github.ya0igoddess.dbsync.config

import org.koin.dsl.module

val DBSyncModule = module {
    includes(repoServiceModule)
}

