package com.github.ya0igoddess.toxicityanalyzer.config

import com.github.ya0igoddess.toxicityanalyzer.service.PythonToxicityCheckService
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton

val toxAnalServiceModule by DI.Module {
    importOnce(toxAnalRepoServiceModule)

    bindEagerSingleton { PythonToxicityCheckService() }
}