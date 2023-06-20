package com.github.ya0igoddess.dbsync.service.settings

import org.yaml.snakeyaml.Yaml
import java.io.File

class FileSettingService : SettingsService  {

    private val file = if (System.getProperty("kordSettings") != null) {
        File(System.getProperty("kordSettings"))
    } else {
        File(this.javaClass.classLoader.getResource("kord-settings.yaml")!!.file)
    }

    private val map: Map<String, Any> = Yaml().load(file.inputStream())

    override fun <T> getValue(code: String): T {
        return map[code] as T
    }

}