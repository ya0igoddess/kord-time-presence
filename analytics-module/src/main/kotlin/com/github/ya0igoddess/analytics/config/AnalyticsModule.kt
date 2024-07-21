package com.github.ya0igoddess.analytics.config

import com.github.ya0igoddess.dbsync.config.importAllOnce
import org.kodein.di.DI

val AnalyticsModule by DI.Module {
    importAllOnce(analyticsServiceModule, analyticsHandlerModule)
}