package com.example.config

import com.example.handlers.SampleHandler
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.dsl.module

val handlerModule = DI.Module {
    importOnce(serviceModule)

    bindSingleton { SampleHandler(instance()) }
}