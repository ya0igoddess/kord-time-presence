package com.mquiz.extension

import com.github.ya0igoddess.dbsync.database.SkaardModuleDatabase
import com.github.ya0igoddess.dbsync.extensions.KodeinExtension
import com.mquiz.config.MusicQuizModule
import com.mquiz.handlers.MusicQuizHandler
import com.mquiz.handlers.RegisterPlay
import org.kodein.di.instance

class MusicQuizExtension : KodeinExtension() {

    companion object {
        const val code = "sample_module"
    }

    override val name: String
        get() = code

    override suspend fun setup() {
        di.addImport(MusicQuizModule)
        val databaseModule: SkaardModuleDatabase by di.instance()
        //databaseModule.addTables(musicQuizModule)
        //val settings: KordDBSettings by di.instance()
        //loadLiquibase(settings.jdbc!!, name, "changelog/sample-module/main-changelog.xml")

        val sampleHandler: MusicQuizHandler by di.instance()

        RegisterPlay()
    }

}