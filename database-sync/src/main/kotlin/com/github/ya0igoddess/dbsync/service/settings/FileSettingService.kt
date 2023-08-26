package com.github.ya0igoddess.dbsync.service.settings

import com.akuleshov7.ktoml.file.TomlFileReader
import com.github.ya0igoddess.dbsync.config.settings.KordDBSettings
import kotlinx.serialization.serializer
import java.io.File
import java.util.function.Supplier

class FileSettingsProvider : Supplier<KordDBSettings> {
    override fun get(): KordDBSettings {
        val settingProperty = System.getProperty("kord-db-sync-settings")
        val file = if (settingProperty != null) {
            File(settingProperty)
        } else {
            File(this.javaClass.classLoader.getResource("settings.toml")?.file
                ?: throw IllegalArgumentException("Settings file is not present"))
        }

        return TomlFileReader.partiallyDecodeFromFile(serializer(), file.absolutePath, "kord-db-sync")
    }
}