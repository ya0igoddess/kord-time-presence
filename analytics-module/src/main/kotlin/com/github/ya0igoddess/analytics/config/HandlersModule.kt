package com.github.ya0igoddess.analytics.config

import org.kodein.di.DI

val analyticsHandlerModule by DI.Module {
    importOnce(analyticsServiceModule)
}