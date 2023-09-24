package com.example.config

import com.example.handlers.SampleHandler
import org.koin.dsl.module

val handlerModule = module {
    includes(serviceModule)

    single<SampleHandler> { SampleHandler(get()) }
}