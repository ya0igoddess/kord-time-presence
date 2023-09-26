package com.github.ya0igoddess.poma.config

import org.koin.dsl.module

val handlerModule = module {
    includes(serviceModule)

}