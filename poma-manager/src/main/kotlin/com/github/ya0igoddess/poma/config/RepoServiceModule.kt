package com.github.ya0igoddess.poma.config

import com.github.ya0igoddess.poma.repositories.IPomaAccountRepoService
import com.github.ya0igoddess.poma.repositories.IPomaRepoService
import com.github.ya0igoddess.poma.repositories.PomaAccountRepoService
import com.github.ya0igoddess.poma.repositories.PomaRepoService
import org.koin.dsl.module

val repoServiceModule = module {
    includes(
        repoModule,
    )

    single<IPomaAccountRepoService> { PomaAccountRepoService(get()) }
    single<IPomaRepoService> { PomaRepoService(get()) }
}