package com.github.ya0igoddess.toxicityanalyzer.config

import org.kodein.di.DI

val toxAnalHandlerModule by DI.Module {
    importOnce(toxAnalServiceModule)

    //bindEagerSingleton { CheckToxicityCommand(instance()) }
}