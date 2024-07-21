package com.github.ya0igoddess.analytics.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.kodein.di.DI

val analyticsRepoModule by DI.Module {
    importOnce(dataBaseModule)

}