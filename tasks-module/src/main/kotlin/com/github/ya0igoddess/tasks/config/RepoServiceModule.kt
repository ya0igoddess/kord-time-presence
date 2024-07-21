package com.github.ya0igoddess.tasks.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import com.github.ya0igoddess.dbsync.config.repoServiceModule
import org.kodein.di.DI

val tasksRepoServiceModule by DI.Module {
    importAllOnce(
        tasksRepoModule,
        repoServiceModule
    )

}