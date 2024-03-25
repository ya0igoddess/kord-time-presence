package com.example.config

import com.example.handlers.SampleHandler
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val sampleHandlerModule by DI.Module {
    importOnce(sampleServiceModule)

    bindEagerSingleton { SampleHandler(instance()) }
}