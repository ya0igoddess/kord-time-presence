package com.github.ya0igoddess.poma.config

import com.github.ya0igoddess.poma.contexts.PomaContextSupplier
import org.koin.dsl.module

val contextModule = module {
    includes(serviceModule)

    single { PomaContextSupplier(get()) }
}