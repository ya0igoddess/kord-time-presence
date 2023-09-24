package com.example.config

import com.example.repositories.IPomaAccountRepoService
import com.example.repositories.IPomaRepoService
import com.example.repositories.PomaAccountRepoService
import com.example.repositories.PomaRepoService
import org.koin.dsl.module

val repoServiceModule = module {
    includes(
        repoModule,
    )

    single<IPomaAccountRepoService> { PomaAccountRepoService(get()) }
    single<IPomaRepoService> { PomaRepoService(get()) }
}