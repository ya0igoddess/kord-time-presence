package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.handlers.VoiceStatusChangeHandler
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val presenceHandlerModule by DI.Module {
    importOnce(presenceServiceModule)

    bindEagerSingleton { VoiceStatusChangeHandler(instance()) }
}