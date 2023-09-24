package com.example.config

import com.example.repositories.PomaAccountRepoService
import com.example.repositories.PomaRepoService
import org.koin.dsl.module

val repoServiceModule = module {
    includes(
        repoModule,
    )

    single<PomaAccountRepoService> { PomaAccountRepoService(get()) }
    single<PomaRepoService> { PomaRepoService(get()) }
}