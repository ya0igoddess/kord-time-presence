package com.example.config

import org.koin.dsl.module

val serviceModule = module {
    includes(repoServiceModule)

}