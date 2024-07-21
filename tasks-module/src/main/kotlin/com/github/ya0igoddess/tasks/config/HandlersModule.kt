package com.github.ya0igoddess.tasks.config

import org.kodein.di.DI

val tasksHandlerModule by DI.Module {
    importOnce(tasksServiceModule)
}