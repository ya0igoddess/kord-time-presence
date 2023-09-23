package com.github.ya0igoddess.presencemonitoring.config

import com.github.ya0igoddess.presencemonitoring.handlers.VoiceStatusChangeHandler
import org.koin.dsl.module

val handlerModule = module {
    includes(serviceModule)

    single<VoiceStatusChangeHandler> { VoiceStatusChangeHandler(get()) }
}