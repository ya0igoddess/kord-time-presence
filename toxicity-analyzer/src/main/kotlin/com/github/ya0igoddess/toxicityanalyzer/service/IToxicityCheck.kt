package com.github.ya0igoddess.toxicityanalyzer.service

import com.github.ya0igoddess.toxicityanalyzer.model.ToxicityCheckResult

fun interface IToxicityCheck {
    suspend fun checkIfMessageToxic(message: String): ToxicityCheckResult
}

