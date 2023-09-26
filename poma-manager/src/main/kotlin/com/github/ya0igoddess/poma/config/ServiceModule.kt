package com.github.ya0igoddess.poma.config

import com.github.ya0igoddess.poma.service.IPomaAccountService
import com.github.ya0igoddess.poma.service.PomaAccountService
import org.koin.dsl.module

val serviceModule = module {
    includes(repoServiceModule)

    single<IPomaAccountService> { PomaAccountService(get(), get(), get()) }
}