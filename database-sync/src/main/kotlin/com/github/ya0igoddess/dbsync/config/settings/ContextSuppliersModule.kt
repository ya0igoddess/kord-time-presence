package com.github.ya0igoddess.dbsync.config.settings

import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.koin.dsl.module

val contextSuppliersModule = module {
    includes(repoServiceModule)

}