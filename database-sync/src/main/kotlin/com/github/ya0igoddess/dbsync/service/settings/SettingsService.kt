package com.github.ya0igoddess.dbsync.service.settings

interface SettingsService {
    fun <T> getValue(code: String): T
}