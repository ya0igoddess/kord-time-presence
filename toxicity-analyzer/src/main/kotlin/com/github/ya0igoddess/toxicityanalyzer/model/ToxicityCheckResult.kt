package com.github.ya0igoddess.toxicityanalyzer.model

data class ToxicityCheckResult(
        val toxic: Boolean,
        val probability: Float
) {
    companion object {
        fun fromString(string: String): ToxicityCheckResult {
            val part = string.split(' ')
            return ToxicityCheckResult(
                    toxic = part[0].lowercase().toBooleanStrict(),
                    probability = part.getOrNull(1)?.toFloat() ?: 1.0f
            )
        }
    }
}