package com.github.ya0igoddess.dbsync.config.settings

import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.kodein.di.DI

val contextSuppliersModule = DI.Module {
    importOnce(repoServiceModule)
}