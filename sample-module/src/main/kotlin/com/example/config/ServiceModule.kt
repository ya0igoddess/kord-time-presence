package com.example.config

import com.example.service.SampleService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val sampleServiceModule by DI.Module {
    importOnce(sampleRepoServiceModule)

    bindEagerSingleton { SampleService(instance()) }
}