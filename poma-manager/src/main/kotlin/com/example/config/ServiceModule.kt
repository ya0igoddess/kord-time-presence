package com.example.config

import com.example.service.IPomaAccountService
import com.example.service.PomaAccountService
import org.koin.dsl.module

val serviceModule = module {
    includes(repoServiceModule)

    single<IPomaAccountService> { PomaAccountService(get(), get(), get()) }
}