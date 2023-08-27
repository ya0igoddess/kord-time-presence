package com.github.ya0igoddess.dbsync.config.settings

import kotlinx.serialization.Serializable

@Serializable
data class KordDBSettings(
    val r2dbc: DBConnectionSettings?,
    val jdbc: DBConnectionSettings?
)

@Serializable
data class DBConnectionSettings(
    val url: String?,
    val user: String?,
    val password: String?,
)