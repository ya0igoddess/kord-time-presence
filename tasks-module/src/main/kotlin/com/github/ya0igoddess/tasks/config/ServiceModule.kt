package com.github.ya0igoddess.tasks.config

import com.github.ya0igoddess.tasks.service.DefaultTaskExecutor
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton

val tasksServiceModule by DI.Module {
    importOnce(tasksRepoServiceModule)

    bindEagerSingleton { DefaultTaskExecutor() }
}