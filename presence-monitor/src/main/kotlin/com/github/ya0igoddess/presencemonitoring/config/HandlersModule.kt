package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.handlers.VoiceStatusChangeHandler
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.dsl.module

val handlerModule by DI.Module {
    importOnce(serviceModule)

    bindSingleton { VoiceStatusChangeHandler(instance()) }
}