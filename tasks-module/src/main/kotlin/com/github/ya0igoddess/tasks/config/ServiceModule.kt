package com.github.ya0igoddess.tasks.config

import org.kodein.di.DI

val tasksServiceModule by DI.Module {
    importOnce(tasksRepoServiceModule)

}