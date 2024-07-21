package com.github.ya0igoddess.tasks.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val TaskModule by DI.Module {
    importAllOnce(tasksServiceModule, tasksHandlerModule)
}