package com.github.ya0igoddess.dbsync.config

import org.kodein.di.DI

fun DI.Builder.importAllOnce(vararg modules: DI.Module) {
    for (module in modules) {
        importOnce(module)
    }
}