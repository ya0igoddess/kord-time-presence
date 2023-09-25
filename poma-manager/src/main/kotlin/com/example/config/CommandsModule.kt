package com.example.config

import com.example.commands.CreatePomaAccountCommand
import com.example.commands.PomaManagerCommand
import org.koin.dsl.module

val commandsModule = module {
    includes(serviceModule)

    single { CreatePomaAccountCommand(get(), get()) }
    single { PomaManagerCommand(get(), get(), get(), get()) }
}