package com.github.ya0igoddess.poma.model

data class Poma(
    val id: Long?,
    val accountId: Long,
    val name: String,
    val level: Int,
    val rarity: Int,
    val strength: Int,
    val agility: Int,
    val intelligence: Int,
)
