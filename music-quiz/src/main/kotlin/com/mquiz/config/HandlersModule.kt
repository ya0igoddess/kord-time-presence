package com.mquiz.config

import com.mquiz.handlers.MusicQuizHandler
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.instance

val musicQuizHandlerModule by DI.Module {
    importOnce(musicQuizServiceModule)

    bindEagerSingleton { MusicQuizHandler(instance()) }
}