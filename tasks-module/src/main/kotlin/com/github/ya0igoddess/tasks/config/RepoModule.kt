package com.github.ya0igoddess.tasks.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.kodein.di.DI

val tasksRepoModule by DI.Module {
    importOnce(dataBaseModule)

}