package com.github.ya0igoddess.analytics.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.kodein.di.DI

val analyticsRepoServiceModule by DI.Module {
    importAllOnce(
        analyticsRepoModule,
        repoServiceModule
    )

}