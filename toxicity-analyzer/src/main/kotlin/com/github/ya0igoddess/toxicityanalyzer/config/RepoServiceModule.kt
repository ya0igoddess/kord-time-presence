package com.github.ya0igoddess.toxicityanalyzer.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.kodein.di.DI

val toxAnalRepoServiceModule by DI.Module {
    importAllOnce(
            toxAnalRepoModule,
            repoServiceModule
    )

}