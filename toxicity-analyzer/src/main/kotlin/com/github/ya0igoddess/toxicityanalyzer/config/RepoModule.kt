package com.github.ya0igoddess.toxicityanalyzer.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import org.kodein.di.DI

val toxAnalRepoModule by DI.Module {
    importOnce(dataBaseModule)

}