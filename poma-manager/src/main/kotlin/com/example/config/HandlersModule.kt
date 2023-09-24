package com.example.config

import org.koin.dsl.module

val handlerModule = module {
    includes(serviceModule)

}