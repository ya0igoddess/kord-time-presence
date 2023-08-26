package com.github.ya0igoddess.dbsync.config.settings

import kotlinx.serialization.Serializable

@Serializable
data class KordDBSettings(
    val r2dbc: R2DBCSettings?
)

@Serializable
data class R2DBCSettings(
    val url: String?,
    val user: String?,
    val password: String?,
)