package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.service.VoiceConnectionRegistry
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val presenceServiceModule by DI.Module {
    importOnce(presenceRepoServiceModule)

    bindEagerSingleton { VoiceConnectionRegistry(instance(), instance(), instance()) }
}