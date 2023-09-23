package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.service.IVoiceConnectionRegistry
import com.github.ya0igoddess.presencemonitoring.service.VoiceConnectionRegistry
import org.koin.dsl.module

val serviceModule = module {
    includes(repoServiceModule)

    single<IVoiceConnectionRegistry> { VoiceConnectionRegistry(get(), get(), get()) }
}