package com.github.ya0igoddess.analytics.config

import org.kodein.di.DI

val analyticsServiceModule by DI.Module {
    importOnce(analyticsRepoServiceModule)

}