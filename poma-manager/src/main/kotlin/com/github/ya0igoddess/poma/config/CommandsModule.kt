package com.github.ya0igoddess.poma.config

import com.github.ya0igoddess.dbsync.config.settings.contextSuppliersModule
import com.github.ya0igoddess.poma.commands.CreatePomaAccountCommand
import com.github.ya0igoddess.poma.commands.PomaManagerCommand
import org.koin.dsl.module

val commandsModule = module {
    includes(serviceModule)
    includes(contextSuppliersModule)

    single { CreatePomaAccountCommand(get(), get()) }
    single { PomaManagerCommand(get(), get(), get()) }
}