package com.example.config

import com.example.service.ISampleService
import com.example.service.SampleService
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.dsl.module

val serviceModule = DI.Module {
    importOnce(repoServiceModule)

    bindSingleton { SampleService(instance()) }
}