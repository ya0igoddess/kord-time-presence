package com.mquiz.config

import com.mquiz.service.SampleService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val musicQuizServiceModule by DI.Module {
    importOnce(musicQuizRepoServiceModule)

    bindEagerSingleton { SampleService(instance()) }
}