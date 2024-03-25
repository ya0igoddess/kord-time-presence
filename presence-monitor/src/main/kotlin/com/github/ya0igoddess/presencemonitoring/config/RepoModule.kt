package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.dbsync.database.dataBaseModule
import com.github.ya0igoddess.presencemonitoring.repositories.VoiceConnectionPeriodCRUDRepo
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val presenceRepoModule by DI.Module {
    importOnce(dataBaseModule)

    bindEagerSingleton { VoiceConnectionPeriodCRUDRepo(instance()) }
}