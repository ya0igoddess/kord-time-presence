package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.service.VoiceConnectionRegistry
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val serviceModule by DI.Module {
    importOnce(repoServiceModule)

    bindSingleton { VoiceConnectionRegistry(instance(), instance(), instance()) }
}