package com.github.ya0igoddess.dbsync.service.settings

import com.akuleshov7.ktoml.file.TomlFileReader
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import kotlinx.serialization.serializer
import java.io.File
import java.util.function.Supplier

class FileSettingsProvider : Supplier<KordDBSettings> {
    override fun get(): KordDBSettings {
        val fileName = System.getenv().getOrDefault("kord.dbsync.settings", "settings.toml")
        val file = File(fileName)
        return TomlFileReader.partiallyDecodeFromFile(serializer(), file.absolutePath, "kord-db-sync")
    }
}