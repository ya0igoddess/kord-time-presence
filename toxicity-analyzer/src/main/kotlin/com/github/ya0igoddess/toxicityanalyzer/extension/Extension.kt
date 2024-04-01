package com.github.ya0igoddess.toxicityanalyzer.extension

import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.github.ya0igoddess.toxicityanalyzer.config.ToxicityAnalyzerModule
import com.github.ya0igoddess.toxicityanalyzer.handlers.registerCheckToxicityCommand
import com.github.ya0igoddess.toxicityanalyzer.service.IToxicityCheck
import org.kodein.di.instance

class ToxicityAnalyzerExtension : KodeinExtension() {

    companion object {
        const val code = "toxicity_analyzer"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(ToxicityAnalyzerModule)
        val toxicityCheckService: IToxicityCheck by di.instance()
        registerCheckToxicityCommand(toxicityCheckService)
    }

}