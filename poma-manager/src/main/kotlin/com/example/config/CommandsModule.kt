package com.example.config

import com.example.commands.CreatePomaAccountCommand
import org.koin.dsl.module

val commandsModule = module {
    includes(serviceModule)

    single { CreatePomaAccountCommand(get(), get()) }
}