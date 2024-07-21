package com.github.ya0igoddess.analytics.extension

import com.github.ya0igoddess.analytics.config.AnalyticsModule
import com.github.ya0igoddess.dbsync.extensions.KodeinExtension

class AnalyticsExtension : KodeinExtension() {

    companion object {
        const val code = "tasks_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(AnalyticsModule)

    }

}